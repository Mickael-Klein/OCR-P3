package com.chatop.chatopApiController;

import com.chatop.chatopApiDTO.UserDTO;
import com.chatop.chatopApiModel.DbUser;
import com.chatop.chatopApiService.JWTService;
import com.chatop.chatopApiService.UserService;
import com.chatop.utils.EntityAndDTOCreation.EntityAndDTOCreationService;
import com.chatop.utils.ReqResModelsAndServices.Request.LoginRequestModel;
import com.chatop.utils.ReqResModelsAndServices.Request.RegisterRequestModel;
import com.chatop.utils.ReqResModelsAndServices.Response.UserResponseService;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserAuthController {

  @Autowired
  private UserService userService;

  @Autowired
  private JWTService jwtService;

  @Autowired
  UserResponseService userResponseService;

  @Autowired
  EntityAndDTOCreationService entityAndDTOCreationService;

  public UserAuthController(JWTService jwtService) {
    this.jwtService = jwtService;
  }

  @PostMapping("/register")
  public ResponseEntity<Object> registerAccount(
    @Valid @RequestBody RegisterRequestModel registerRequestUser,
    BindingResult bindingResult
  ) {
    try {
      Boolean isRequestPayloadInvalid = bindingResult.hasErrors();
      if (isRequestPayloadInvalid) {
        return ResponseEntity
          .badRequest()
          .body(userResponseService.getRegisteringBadCredentialsJsonString());
      }

      Boolean emailAlreadyRegistered = userService.isEmailAlreadyUsed(
        registerRequestUser.getEmail()
      );
      if (emailAlreadyRegistered) {
        return ResponseEntity
          .badRequest()
          .body(userResponseService.getRegisteringEmailAlreadyUsedJsonString());
      }

      DbUser factoryUser = entityAndDTOCreationService.getFactoryUserRegisterEntity(
        registerRequestUser
      );
      DbUser user = userService.saveUser(factoryUser);

      String jwtToken = jwtService.generateToken(user.getId());

      return ResponseEntity
        .ok()
        .body(userResponseService.getJwtTokenJsonString(jwtToken));
    } catch (Exception e) {
      return ResponseEntity
        .badRequest()
        .body(userResponseService.getRegisteringErrorJsonString());
    }
  }

  @PostMapping("/login")
  public ResponseEntity<Object> login(
    @Valid @RequestBody LoginRequestModel request,
    BindingResult bindingResult
  ) {
    try {
      Boolean isRequestPayloadInvalid = bindingResult.hasErrors();
      if (isRequestPayloadInvalid) {
        return handleInvalidCredentials();
      }

      Optional<DbUser> optionalUser = userService.getUserByEmail(
        request.getEmail()
      );
      if (!optionalUser.isPresent()) {
        return handleInvalidCredentials();
      }

      DbUser user = optionalUser.get();

      Boolean isPasswordCorrect = userService.isPasswordValid(
        request.getPassword(),
        user
      );
      if (!isPasswordCorrect) {
        return handleInvalidCredentials();
      }

      String jwtToken = jwtService.generateToken(user.getId());

      return ResponseEntity
        .ok()
        .body(userResponseService.getJwtTokenJsonString(jwtToken));
    } catch (Exception e) {
      return ResponseEntity
        .badRequest()
        .body(userResponseService.getLoginErrorJsonString());
    }
  }

  private ResponseEntity<Object> handleInvalidCredentials() {
    return ResponseEntity
      .badRequest()
      .body(userResponseService.getLoginBadCredentialsJsonString());
  }

  @GetMapping("/me")
  public ResponseEntity<Object> getMe(@AuthenticationPrincipal Jwt jwt) {
    try {
      Long userId = jwtService.getUserIdFromJwtLong(jwt);
      Optional<DbUser> optionalUser = userService.getUserById(userId);
      if (!optionalUser.isPresent()) {
        return ResponseEntity
          .badRequest()
          .body(userResponseService.getJwtInvalidJwtJsonString());
      }
      DbUser user = optionalUser.get();

      UserDTO userDTO = entityAndDTOCreationService.getFactoryUserDTO(user);

      return ResponseEntity.ok().body(userDTO);
    } catch (Exception e) {
      return ResponseEntity
        .badRequest()
        .body(userResponseService.getJwtInvalidJwtJsonString());
    }
  }
}

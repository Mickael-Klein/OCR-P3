package com.chatop.chatopApiController;

import com.chatop.chatopApiDTO.UserDTO;
import com.chatop.chatopApiModel.DbUser;
import com.chatop.chatopApiService.JWTService;
import com.chatop.chatopApiService.UserService;
import com.chatop.utils.ReqResModel.Request.LoginRequest;
import com.chatop.utils.ReqResModel.Request.RegisterRequest;
import com.chatop.utils.ReqResModel.Response.UserResponseService;
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

  public UserAuthController(JWTService jwtService) {
    this.jwtService = jwtService;
  }

  @PostMapping("/register")
  public ResponseEntity<Object> registerAccount(
    @Valid @RequestBody RegisterRequest request,
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
        request.getEmail()
      );
      if (emailAlreadyRegistered) {
        return ResponseEntity
          .badRequest()
          .body(userResponseService.getRegisteringEmailAlreadyUsedJsonString());
      }

      DbUser user = userService.saveUser(request);

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
    @Valid @RequestBody LoginRequest request,
    BindingResult bindingResult
  ) {
    try {
      Boolean isRequestPayloadInvalid = bindingResult.hasErrors();
      if (isRequestPayloadInvalid) {
        return ResponseEntity
          .badRequest()
          .body(userResponseService.getLoginBadCredentialsJsonString());
      }

      Optional<DbUser> optionalUser = userService.getUserByEmail(
        request.getEmail()
      );
      if (!optionalUser.isPresent()) {
        return ResponseEntity
          .badRequest()
          .body(userResponseService.getLoginBadCredentialsJsonString());
      }

      DbUser user = optionalUser.get();

      Boolean isPasswordCorrect = userService.isPasswordValid(
        request.getPassword(),
        user
      );
      if (!isPasswordCorrect) {
        return ResponseEntity
          .badRequest()
          .body(userResponseService.getLoginBadCredentialsJsonString());
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

  @GetMapping("/me")
  public ResponseEntity<Object> getMe(@AuthenticationPrincipal Jwt jwt) {
    try {
      String userId = jwt.getSubject();
      Optional<DbUser> optionalUser = userService.getUserById(
        Long.parseLong(userId)
      );
      if (!optionalUser.isPresent()) {
        return ResponseEntity
          .badRequest()
          .body(userResponseService.getJwtInvalidJwtJsonString());
      }
      DbUser user = optionalUser.get();

      UserDTO userDTO = new UserDTO();
      userDTO.setId(user.getId());
      userDTO.setName(user.getName());
      userDTO.setEmail(user.getEmail());
      userDTO.setCreated_at(user.getCreatedAt().toLocalDate());
      userDTO.setUpdated_at(user.getUpdatedAt().toLocalDate());

      return ResponseEntity.ok().body(userDTO);
    } catch (Exception e) {
      return ResponseEntity
        .badRequest()
        .body(userResponseService.getJwtInvalidJwtJsonString());
    }
  }
}

package com.chatop.chatopApiController;

import com.chatop.ReqResModel.Request.LoginRequest;
import com.chatop.ReqResModel.Request.RegisterRequest;
import com.chatop.chatopApiDTO.UserDTO;
import com.chatop.chatopApiModel.DbUser;
import com.chatop.chatopApiService.JWTService;
import com.chatop.chatopApiService.UserService;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
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
          .body("{'message' : 'Bad credentials for registering'}");
      }

      Boolean emailAlreadyRegistered = userService.isEmailAlreadyUsed(
        request.getEmail()
      );
      if (emailAlreadyRegistered) {
        return ResponseEntity
          .badRequest()
          .body("{'message' : 'Email is already registered'}");
      }

      DbUser user = userService.saveUser(request);

      String jwtToken = jwtService.generateToken(user.getId());

      Map<String, String> reponseToken = new HashMap<>();
      reponseToken.put("token", jwtToken);

      return ResponseEntity.ok().body(reponseToken);
    } catch (Exception e) {
      return ResponseEntity
        .badRequest()
        .body("{'message' : 'Error on adding user'}");
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
          .body("{'message' : 'Bad credentials for login'}");
      }

      Optional<DbUser> optionalUser = userService.getUserByEmail(
        request.getEmail()
      );
      if (!optionalUser.isPresent()) {
        return ResponseEntity
          .badRequest()
          .body("{'message' : 'Bad credentials for login'}");
      }

      DbUser user = optionalUser.get();

      Boolean isPasswordCorrect = userService.isPasswordValid(
        request.getPassword(),
        user
      );
      if (!isPasswordCorrect) {
        return ResponseEntity
          .badRequest()
          .body("{'message' : 'Bad credentials for login'}");
      }

      String jwtToken = jwtService.generateToken(user.getId());

      Map<String, String> responseToken = new HashMap<>();
      responseToken.put("token", jwtToken);

      return ResponseEntity.ok().body(responseToken);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("{'message' : 'Error on Login'}");
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
        return ResponseEntity.badRequest().body("{'message' : 'invalid jwt'}");
      }
      DbUser user = optionalUser.get();

      UserDTO userDTO = new UserDTO();
      userDTO.setId(user.getId());
      userDTO.setName(user.getName());
      userDTO.setEmail(user.getEmail());
      userDTO.setCreatedAt(user.getCreatedAt().toLocalDate());
      userDTO.setUpdatedAt(user.getUpdatedAt().toLocalDate());

      return ResponseEntity.ok().body(userDTO);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("{'message' : 'invalid jwt'}");
    }
  }
}

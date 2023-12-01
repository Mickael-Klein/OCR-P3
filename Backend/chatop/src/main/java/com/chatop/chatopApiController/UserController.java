package com.chatop.chatopApiController;

import com.chatop.ReqResModel.Request.LoginRequest;
import com.chatop.ReqResModel.Request.RegisterRequest;
import com.chatop.chatopApiModel.DbUser;
import com.chatop.chatopApiService.JWTService;
import com.chatop.chatopApiService.UserService;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private JWTService jwtService;

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
          .body("{'message' : 'Email already registered'}");
      }

      DbUser user = userService.saveUser(request);

      String jwtToken = jwtService.generateToken(/* TODO */);

      return ResponseEntity.ok().body("{'token' : " + jwtToken + "}");
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

      String jwtToken = jwtService.generateToken(/* TODO */);

      return ResponseEntity.ok().body("{'token' : " + jwtToken + "}");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("{'message' : 'Error on Login'}");
    }
  }
}

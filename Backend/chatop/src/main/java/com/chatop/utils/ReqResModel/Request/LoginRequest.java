package com.chatop.utils.ReqResModel.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {

  @NotBlank(message = "A login email is required for login")
  String email;

  @NotBlank(message = "A password is required for login")
  String password;
}

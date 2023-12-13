package com.chatop.utils.ReqResModelsAndServices.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestModel {

  @NotBlank(message = "A login email is required for login")
  String email;

  @NotBlank(message = "A password is required for login")
  String password;
}

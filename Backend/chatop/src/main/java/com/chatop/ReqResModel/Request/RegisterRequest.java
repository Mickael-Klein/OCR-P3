package com.chatop.ReqResModel.Request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequest {

  @NotBlank(message = "An email is required")
  @Pattern(
    regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",
    message = "The email format provided is incorrect, expected (something@xyz.com)"
  )
  String email;

  @NotBlank(message = "A name is required")
  @Pattern(
    regexp = "^[a-zA-ZÀ-ÿ'-]+ [a-zA-ZÀ-ÿ'-]+$",
    message = "The name format provided is incorrect, expected (firstname lastname)"
  )
  String name;

  @NotBlank(message = "A password is required")
  @Pattern(
    regexp = "^.{6,}$",
    message = "Password must be at least 6 characters long"
  )
  String password;
}

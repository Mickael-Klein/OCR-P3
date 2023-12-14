package com.chatop.utils.RequestModel;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * Data model for user registration request.
 */
@Data
public class RegisterRequestModel {

  /**
   * The email address for registration.
   */
  @NotBlank(message = "An email is required")
  @Pattern(
    regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",
    message = "The email format provided is incorrect, expected (something@xyz.com)"
  )
  @Schema(example = "user@example.com")
  private String email;

  /**
   * The name for registration.
   */
  @NotBlank(message = "A name is required")
  @Pattern(
    regexp = "^[a-zA-ZÀ-ÿ'-]+ [a-zA-ZÀ-ÿ'-]+$",
    message = "The name format provided is incorrect, expected (firstname lastname)"
  )
  @Schema(example = "User Example")
  private String name;

  /**
   * The password for registration.
   */
  @NotBlank(message = "A password is required")
  @Pattern(
    regexp = "^.{6,}$",
    message = "Password must be at least 6 characters long"
  )
  @Schema(example = "password")
  private String password;
}

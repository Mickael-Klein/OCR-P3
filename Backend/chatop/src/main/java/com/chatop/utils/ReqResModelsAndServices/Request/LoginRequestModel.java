package com.chatop.utils.ReqResModelsAndServices.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Data model for a login request.
 */
@Data
public class LoginRequestModel {

  /**
   * The email used for login.
   */
  @NotBlank(message = "A login email is required for login")
  @Schema(example = "test@example.com")
  private String email;

  /**
   * The password used for login.
   */
  @NotBlank(message = "A password is required for login")
  @Schema(example = "password")
  private String password;
}

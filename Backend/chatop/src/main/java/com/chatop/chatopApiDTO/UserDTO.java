package com.chatop.chatopApiDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for representing user information.
 */
@Data
@Schema(
  description = "Data Transfer Object (DTO) for representing user information."
)
public class UserDTO {

  @Schema(description = "The unique identifier for the user.", example = "0")
  private long id;

  @Schema(description = "The name of the user.", example = "User Example")
  private String name;

  @Schema(
    description = "The email address of the user.",
    example = "user@example.com"
  )
  private String email;

  @Schema(
    description = "The date when the user was created.",
    example = "yyyy-MM-dd"
  )
  private LocalDate created_at;

  @Schema(
    description = "The date when the user was last updated.",
    example = "yyyy-MM-dd"
  )
  private LocalDate updated_at;
}

package com.chatop.utils.RequestInput;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Data model for a message request.
 */
@Data
public class MessageRequestInput {

  /**
   * The text message.
   */
  @NotBlank(message = "A text message is required")
  @Schema(example = "message")
  private String message;

  /**
   * The user ID associated with the message.
   */
  @NotNull(message = "A user id is required")
  @Schema(example = "0")
  private Long user_id;

  /**
   * The rental ID associated with the message.
   */
  @NotNull(message = "A rental id is required")
  @Schema(example = "0")
  private Long rental_id;
}

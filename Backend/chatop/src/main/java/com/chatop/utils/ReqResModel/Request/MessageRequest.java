package com.chatop.utils.ReqResModel.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageRequest {

  @NotBlank(message = "A text message is required")
  String message;

  @NotNull(message = "A user id is required")
  Long user_id;

  @NotNull(message = "A rental id is required")
  Long rental_id;
}

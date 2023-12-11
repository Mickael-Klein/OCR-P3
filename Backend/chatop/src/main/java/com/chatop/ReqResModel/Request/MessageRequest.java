package com.chatop.ReqResModel.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageRequest {

  @NotBlank(message = "A text message is required")
  String message;

  @NotNull(message = "A user id is required")
  Long userId;

  @NotNull(message = "A rental id is required")
  Long rentalId;
}

package com.chatop.utils.SwaggerApiResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SwaggerApiMessageResponseModel {

  @Schema(example = "string")
  private String message;
}

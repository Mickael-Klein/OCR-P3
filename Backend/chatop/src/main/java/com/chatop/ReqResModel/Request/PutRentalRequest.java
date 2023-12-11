package com.chatop.ReqResModel.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class PutRentalRequest {

  @NotBlank(message = "The rental name must be provided")
  String name;

  @NotNull(message = "The rental surface must be provided")
  BigDecimal surface;

  @NotNull(message = "The rental price must be provided")
  BigDecimal price;

  @NotBlank(message = "The rental description must be provided")
  String description;
}

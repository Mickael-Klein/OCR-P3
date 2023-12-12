package com.chatop.utils.ReqResModel.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddRentalRequest {

  @NotBlank(message = "The rental name must be provided")
  String name;

  @NotNull(message = "The rental surface must be provided")
  BigDecimal surface;

  @NotNull(message = "The rental price must be provided")
  BigDecimal price;

  private MultipartFile picture;

  @NotBlank(message = "The rental description must be provided")
  String description;
}

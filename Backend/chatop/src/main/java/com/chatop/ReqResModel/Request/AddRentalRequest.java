package com.chatop.ReqResModel.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddRentalRequest {

  @NotBlank(message = "The rental name must be provided")
  String name;

  @NotNull(message = "The rental surface must be provided")
  Long surface;

  @NotNull(message = "The rental price must be provided")
  Long price;

  private MultipartFile picture;

  @NotBlank(message = "The rental description must be provided")
  String description;
}

package com.chatop.utils.RequestInput;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Data model for adding a rental request.
 */
@Data
public class AddRentalRequestInput {

  /**
   * The name of the rental.
   */
  @NotBlank(message = "The rental name must be provided")
  @Schema(example = "Rental Name")
  private String name;

  /**
   * The surface area of the rental.
   */
  @NotNull(message = "The rental surface must be provided")
  @Schema(example = "0")
  private BigDecimal surface;

  /**
   * The price of the rental.
   */
  @NotNull(message = "The rental price must be provided")
  @Schema(example = "0")
  private BigDecimal price;

  /**
   * The picture associated with the rental.
   */
  @Schema(example = "image file")
  private MultipartFile picture;

  /**
   * The description of the rental.
   */
  @NotBlank(message = "The rental description must be provided")
  @Schema(example = "Rental description")
  private String description;
}

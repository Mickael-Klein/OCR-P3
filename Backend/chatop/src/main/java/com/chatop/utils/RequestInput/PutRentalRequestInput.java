package com.chatop.utils.RequestInput;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;

/**
 * Data model for updating a rental request.
 */
@Data
public class PutRentalRequestInput {

  /**
   * The updated name of the rental.
   */
  @NotBlank(message = "The rental name must be provided")
  @Schema(example = "Rental Name")
  private String name;

  /**
   * The updated surface area of the rental.
   */
  @NotNull(message = "The rental surface must be provided")
  @Schema(example = "0")
  private BigDecimal surface;

  /**
   * The updated price of the rental.
   */
  @NotNull(message = "The rental price must be provided")
  @Schema(example = "0")
  private BigDecimal price;

  /**
   * The updated description of the rental.
   */
  @NotBlank(message = "The rental description must be provided")
  @Schema(example = "Rental description")
  private String description;
}

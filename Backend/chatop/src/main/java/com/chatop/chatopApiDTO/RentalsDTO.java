package com.chatop.chatopApiDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for representing rental properties.
 */
@Data
@Schema(
  description = "Data Transfer Object (DTO) for representing rental properties."
)
public class RentalsDTO {

  @Schema(
    description = "The unique identifier for the rental property.",
    example = "0"
  )
  private Long id;

  @Schema(
    description = "The name of the rental property.",
    example = "Rental Name"
  )
  private String name;

  @Schema(
    description = "The surface area of the rental property.",
    example = "0"
  )
  private BigDecimal surface;

  @Schema(description = "The price of the rental property.", example = "0")
  private BigDecimal price;

  @Schema(
    description = "The picture URL of the rental property.",
    example = "http://server:port/directory/rental.jpg"
  )
  private String picture;

  @Schema(
    description = "The description of the rental property.",
    example = "Rental description"
  )
  private String description;

  @Schema(
    description = "The unique identifier of the owner of the rental property.",
    example = "0"
  )
  private Long owner_id;

  @Schema(
    description = "The date when the rental property was created.",
    example = "yyyy-MM-dd"
  )
  private LocalDate created_at;

  @Schema(
    description = "The date when the rental property was last updated.",
    example = "yyyy-MM-dd"
  )
  private LocalDate updated_at;
}

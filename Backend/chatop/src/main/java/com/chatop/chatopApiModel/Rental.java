package com.chatop.chatopApiModel;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * Represents a rental property in the system.
 */
@Data
@Entity
@Table(name = "rentals")
@Schema(description = "Represents a rental property in the database.")
public class Rental {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "The unique identifier for the rental property.")
  private Long id;

  @Column(name = "name")
  @Schema(description = "The name of the rental property.")
  private String name;

  @Column(name = "surface")
  @Schema(description = "The surface area of the rental property.")
  private BigDecimal surface;

  @Column(name = "price")
  @Schema(description = "The price of the rental property.")
  private BigDecimal price;

  @Column(name = "picture")
  @Schema(description = "The picture URL of the rental property.")
  private String picture;

  @Column(name = "description")
  @Schema(description = "The description of the rental property.")
  private String description;

  @Column(name = "owner_id")
  @Schema(
    description = "The unique identifier of the owner of the rental property."
  )
  private long ownerId;

  @Column(name = "created_at")
  @Schema(
    description = "The timestamp indicating when the rental property was created."
  )
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  @Schema(
    description = "The timestamp indicating when the rental property was last updated."
  )
  private LocalDateTime updatedAt;
}

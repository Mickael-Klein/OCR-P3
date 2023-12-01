package com.chatop.chatopApiModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "rentals")
public class Rental {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "surface")
  private BigDecimal surface;

  @Column(name = "price")
  private BigDecimal price;

  @Column(name = "picture")
  private String picture;

  @Column(name = "description")
  private String description;

  @Column(name = "owner_id")
  private long ownerId;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;
}

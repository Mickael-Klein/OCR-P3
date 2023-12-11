package com.chatop.chatopApiDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class RentalsDTO {

  private Long id;
  private String name;
  private BigDecimal surface;
  private BigDecimal price;
  private String picture;
  private String description;
  private Long ownerId;
  private LocalDate created_at;
  private LocalDate updated_at;
}

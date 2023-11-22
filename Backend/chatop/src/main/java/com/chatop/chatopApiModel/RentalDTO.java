package com.chatop.chatopApiModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class RentalDTO {

  private Long id;
  private String name;
  private BigDecimal surface;
  private BigDecimal price;
  private List<String> picture;
  private String description;
  private Long owner_id;
  private LocalDateTime created_at;
  private LocalDateTime updated_at;
}

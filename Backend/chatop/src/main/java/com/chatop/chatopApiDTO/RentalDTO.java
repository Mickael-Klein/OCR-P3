package com.chatop.chatopApiDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
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
  private LocalDate created_at;
  private LocalDate updated_at;
}

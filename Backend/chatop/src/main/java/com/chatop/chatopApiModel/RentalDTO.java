package com.chatop.chatopApiModel;

import java.math.BigDecimal;
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
  private String created_at;
  private String updated_at;
}

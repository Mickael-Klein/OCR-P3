package com.chatop.chatopApiDTO;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class RentalsDTO { // This represent an element of the list of rentals which will be send in rentals/ get all method

  private Long id;
  private String name;
  private BigDecimal surface;
  private BigDecimal price;
  private String picture; // String instead of List<String>, compare to RentalDTO class
  private String description;
  private Long ownerId;
  private String created_at;
  private String updated_at;
}

package com.chatop.chatopApiDTO;

import java.time.LocalDate;
import lombok.Data;

@Data
public class UserDTO {

  private Long id;
  private String name;
  private String email;
  private LocalDate createdAt;
  private LocalDate updatedAt;
}

package com.chatop.chatopApiModel;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * Represents a user in the database.
 */
@Data
@Entity
@Table(name = "users")
@Schema(description = "Represents a user in the database.")
public class DbUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "The unique identifier for the user.")
  private Long id;

  @Column(name = "email")
  @Schema(description = "The email address of the user.")
  private String email;

  @Column(name = "name")
  @Schema(description = "The name of the user.")
  private String name;

  @Column(name = "password")
  @Schema(description = "The password of the user.")
  private String password;

  @Column(name = "created_at")
  @Schema(description = "The timestamp indicating when the user was created.")
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  @Schema(
    description = "The timestamp indicating when the user was last updated."
  )
  private LocalDateTime updatedAt;
}

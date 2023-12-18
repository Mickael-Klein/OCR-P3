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
 * Represents a message in the database.
 */
@Data
@Entity
@Table(name = "messages")
@Schema(description = "Represents a message in the system.")
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "The unique identifier for the message.")
  private long id;

  @Column(name = "rental_id")
  @Schema(
    description = "The unique identifier for the rental associated with the message."
  )
  private long rental_id;

  @Column(name = "user_id")
  @Schema(
    description = "The unique identifier for the user who sent the message."
  )
  private long user_id;

  @Column(name = "message")
  @Schema(description = "The content of the message.")
  private String message;

  @Column(name = "created_at")
  @Schema(
    description = "The timestamp indicating when the message was created."
  )
  private LocalDateTime created_at;

  @Column(name = "updated_at")
  @Schema(
    description = "The timestamp indicating when the message was last updated."
  )
  private LocalDateTime updated_at;
}

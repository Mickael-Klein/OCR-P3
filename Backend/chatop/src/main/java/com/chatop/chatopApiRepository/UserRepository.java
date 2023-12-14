package com.chatop.chatopApiRepository;

import com.chatop.chatopApiModel.DbUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing users in the database.
 *
 * This interface extends JpaRepository, providing CRUD operations for DbUser entities.
 */
@Repository
public interface UserRepository extends JpaRepository<DbUser, Long> {
  /**
   * Retrieves a user by their email address.
   *
   * @param email The email address of the user.
   * @return An Optional containing the user associated with the provided email, or empty if not found.
   */
  Optional<DbUser> findByEmail(String email);
}

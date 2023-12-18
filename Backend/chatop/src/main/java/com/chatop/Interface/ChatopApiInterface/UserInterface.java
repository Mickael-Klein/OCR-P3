package com.chatop.Interface.ChatopApiInterface;

import com.chatop.chatopApiModel.DbUser;
import java.util.Optional;

/**
 * Interface for user service operations.
 */
public interface UserInterface {
  /**
   * Checks if the given email is already used.
   *
   * @param email The email to check.
   * @return True if the email is already used, otherwise false.
   */
  boolean isEmailAlreadyUsed(String email);

  /**
   * Saves a user.
   *
   * @param user The user to be saved.
   * @return The saved user.
   */
  DbUser saveUser(DbUser user);

  /**
   * Retrieves a user by email.
   *
   * @param email The email of the user to retrieve.
   * @return An Optional containing the retrieved user, or empty if not found.
   */
  Optional<DbUser> getUserByEmail(String email);

  /**
   * Retrieves a user by ID.
   *
   * @param id The ID of the user to retrieve.
   * @return An Optional containing the retrieved user, or empty if not found.
   */
  Optional<DbUser> getUserById(long id);

  /**
   * Checks if the provided password is valid for the given user.
   *
   * @param password The password to check.
   * @param user     The user to validate the password against.
   * @return True if the password is valid, otherwise false.
   */
  boolean isPasswordValid(String password, DbUser user);
}

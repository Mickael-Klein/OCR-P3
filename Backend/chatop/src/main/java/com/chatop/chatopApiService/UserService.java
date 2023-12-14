package com.chatop.chatopApiService;

import com.chatop.Interface.ChatopApiServiceInterface.UserServiceInterface;
import com.chatop.chatopApiModel.DbUser;
import com.chatop.chatopApiRepository.UserRepository;
import java.util.Optional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service for handling user-related operations.
 */
@Data
@Service
public class UserService implements UserServiceInterface {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  /**
   * Checks if the given email is already associated with a user.
   *
   * @param email The email address to check.
   * @return True if the email is already used, false otherwise.
   */
  @Override
  public Boolean isEmailAlreadyUsed(String email) {
    Optional<DbUser> optionalUser = userRepository.findByEmail(email);
    return optionalUser.isPresent();
  }

  /**
   * Saves a user in the database.
   *
   * @param user The user to be saved.
   * @return The saved user.
   */
  @Override
  public DbUser saveUser(DbUser user) {
    return userRepository.save(user);
  }

  /**
   * Retrieves a user by their email address.
   *
   * @param email The email address of the user.
   * @return An Optional containing the user associated with the provided email, or empty if not found.
   */
  @Override
  public Optional<DbUser> getUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  /**
   * Retrieves a user by their unique identifier.
   *
   * @param id The unique identifier of the user.
   * @return An Optional containing the user associated with the provided ID, or empty if not found.
   */
  @Override
  public Optional<DbUser> getUserById(Long id) {
    return userRepository.findById(id);
  }

  /**
   * Checks if the provided password is valid for the given user.
   *
   * @param password The password to check.
   * @param user     The user for whom to check the password.
   * @return True if the password is valid, false otherwise.
   */
  @Override
  public Boolean isPasswordValid(String password, DbUser user) {
    return passwordEncoder.matches(password, user.getPassword());
  }
}

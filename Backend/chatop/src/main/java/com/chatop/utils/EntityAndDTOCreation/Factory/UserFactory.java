package com.chatop.utils.EntityAndDTOCreation.Factory;

import com.chatop.Interface.UtilEntityAndDTOCreationInterface.FactoryInterface.UserFactoryInterface;
import com.chatop.chatopApiDTO.UserDTO;
import com.chatop.chatopApiModel.DbUser;
import com.chatop.utils.RequestInput.RegisterRequestInput;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Factory component for creating User entities and DTOs.
 */
@Component
public class UserFactory implements UserFactoryInterface {

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  /**
   * Creates a User entity for user registration based on the provided information.
   *
   * @param registerRequestUser The RegisterRequestModel containing information for creating the User.
   * @return The created User entity.
   */
  @Override
  public DbUser getPostUserEntity(RegisterRequestInput registerRequestUser) {
    LocalDateTime now = LocalDateTime.now();

    DbUser user = new DbUser();
    user.setName(registerRequestUser.getName());
    user.setEmail(registerRequestUser.getEmail());
    user.setPassword(
      bCryptPasswordEncoder.encode(registerRequestUser.getPassword())
    );
    user.setCreatedAt(now);
    user.setUpdatedAt(now);

    return user;
  }

  /**
   * Creates a UserDTO based on the provided User entity.
   *
   * @param user The User entity.
   * @return The created UserDTO.
   */
  @Override
  public UserDTO getUserDTO(DbUser user) {
    UserDTO userDTO = new UserDTO();
    userDTO.setId(user.getId());
    userDTO.setName(user.getName());
    userDTO.setEmail(user.getEmail());
    userDTO.setCreated_at(user.getCreatedAt().toLocalDate());
    userDTO.setUpdated_at(user.getUpdatedAt().toLocalDate());

    return userDTO;
  }
}

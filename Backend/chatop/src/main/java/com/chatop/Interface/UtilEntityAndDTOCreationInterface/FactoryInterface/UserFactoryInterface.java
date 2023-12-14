package com.chatop.Interface.UtilEntityAndDTOCreationInterface.FactoryInterface;

import com.chatop.chatopApiDTO.UserDTO;
import com.chatop.chatopApiModel.DbUser;
import com.chatop.utils.RequestModel.RegisterRequestModel;

/**
 * Interface for user factory operations.
 */
public interface UserFactoryInterface {
  /**
   * Creates a new user entity based on the provided registration request model.
   *
   * @param registerRequestUser The registration request model containing user details.
   * @return The created user entity.
   */
  DbUser getPostUserEntity(RegisterRequestModel registerRequestUser);

  /**
   * Creates a DTO (Data Transfer Object) for a user entity.
   *
   * @param user The user entity to create a DTO for.
   * @return The DTO representing the user.
   */
  UserDTO getUserDTO(DbUser user);
}

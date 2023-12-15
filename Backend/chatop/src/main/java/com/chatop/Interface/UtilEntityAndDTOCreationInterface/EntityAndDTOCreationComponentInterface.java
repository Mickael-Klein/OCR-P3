<<<<<<<< HEAD:Backend/chatop/src/main/java/com/chatop/Interface/EntityAndDTOCreationInterface/EntityAndDTOCreationServiceInterface.java
package com.chatop.Interface.EntityAndDTOCreationInterface;
========
package com.chatop.Interface.UtilEntityAndDTOCreationInterface;
>>>>>>>> dev:Backend/chatop/src/main/java/com/chatop/Interface/UtilEntityAndDTOCreationInterface/EntityAndDTOCreationComponentInterface.java

import com.chatop.chatopApiDTO.RentalsDTO;
import com.chatop.chatopApiDTO.UserDTO;
import com.chatop.chatopApiModel.DbUser;
import com.chatop.chatopApiModel.Message;
import com.chatop.chatopApiModel.Rental;
import com.chatop.utils.RequestInput.AddRentalRequestInput;
import com.chatop.utils.RequestInput.MessageRequestInput;
import com.chatop.utils.RequestInput.PutRentalRequestInput;
import com.chatop.utils.RequestInput.RegisterRequestInput;

/**
 * Interface for entity and DTO creation component operations.
 */
public interface EntityAndDTOCreationComponentInterface {
  /**
   * Creates a message entity based on the provided message request model.
   *
   * @param messageRequest The message request model containing message details.
   * @return The created message entity.
   */
  Message getFactoryMessageEntity(MessageRequestInput messageRequest);

  /**
   * Creates a new rental entity for a post request.
   *
   * @param userId                The ID of the user creating the rental.
   * @param imageUrl              The URL of the rental picture.
   * @param postRentalRequest     The request model for posting a new rental.
   * @return The created rental entity.
   */
  Rental getFactoryRentalPostEntity(
    Long userId,
    String imageUrl,
    AddRentalRequestInput postRentalRequest
  );

  /**
   * Creates a modified rental entity for a put request.
   *
   * @param currentRental         The current state of the rental.
   * @param putRentalRequest      The request model for updating an existing rental.
   * @return The modified rental entity.
   */
  Rental getFactoryRentalPutEntity(
    Rental currentRental,
    PutRentalRequestInput putRentalRequest
  );

  /**
   * Creates a DTO (Data Transfer Object) for a rental entity.
   *
   * @param rental                The rental entity to create a DTO for.
   * @return The DTO representing the rental.
   */
  RentalsDTO getFactoryRentalsDTO(Rental rental);

  /**
   * Creates a new user entity based on the provided registration request model.
   *
   * @param registerRequestUser The registration request model containing user details.
   * @return The created user entity.
   */
  DbUser getFactoryUserRegisterEntity(RegisterRequestInput registerRequestUser);

  /**
   * Creates a DTO (Data Transfer Object) for a user entity.
   *
   * @param user The user entity to create a DTO for.
   * @return The DTO representing the user.
   */
  UserDTO getFactoryUserDTO(DbUser user);
}

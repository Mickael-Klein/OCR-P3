package com.chatop.utils.Interface.EntityAndDTOCreationInterface;

import com.chatop.chatopApiDTO.RentalsDTO;
import com.chatop.chatopApiDTO.UserDTO;
import com.chatop.chatopApiModel.DbUser;
import com.chatop.chatopApiModel.Message;
import com.chatop.chatopApiModel.Rental;
import com.chatop.utils.ReqResModelsAndServices.Request.AddRentalRequestModel;
import com.chatop.utils.ReqResModelsAndServices.Request.MessageRequestModel;
import com.chatop.utils.ReqResModelsAndServices.Request.PutRentalRequestModel;
import com.chatop.utils.ReqResModelsAndServices.Request.RegisterRequestModel;

/**
 * Interface for entity and DTO creation service operations.
 */
public interface EntityAndDTOCreationServiceInterface {
  /**
   * Creates a message entity based on the provided message request model.
   *
   * @param messageRequest The message request model containing message details.
   * @return The created message entity.
   */
  Message getFactoryMessageEntity(MessageRequestModel messageRequest);

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
    AddRentalRequestModel postRentalRequest
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
    PutRentalRequestModel putRentalRequest
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
  DbUser getFactoryUserRegisterEntity(RegisterRequestModel registerRequestUser);

  /**
   * Creates a DTO (Data Transfer Object) for a user entity.
   *
   * @param user The user entity to create a DTO for.
   * @return The DTO representing the user.
   */
  UserDTO getFactoryUserDTO(DbUser user);
}

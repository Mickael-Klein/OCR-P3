package com.chatop.utils.EntityAndDTOCreation;

import com.chatop.Interface.UtilEntityAndDTOCreationInterface.EntityAndDTOCreationComponentInterface;
import com.chatop.chatopApiDTO.RentalsDTO;
import com.chatop.chatopApiDTO.UserDTO;
import com.chatop.chatopApiModel.DbUser;
import com.chatop.chatopApiModel.Message;
import com.chatop.chatopApiModel.Rental;
import com.chatop.utils.EntityAndDTOCreation.Factory.MessageFactory;
import com.chatop.utils.EntityAndDTOCreation.Factory.RentalFactory;
import com.chatop.utils.EntityAndDTOCreation.Factory.UserFactory;
import com.chatop.utils.RequestModel.AddRentalRequestModel;
import com.chatop.utils.RequestModel.MessageRequestModel;
import com.chatop.utils.RequestModel.PutRentalRequestModel;
import com.chatop.utils.RequestModel.RegisterRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Component for creating entities and DTOs using various factories.
 */
@Component
public class EntityAndDTOCreationComponent
  implements EntityAndDTOCreationComponentInterface {

  @Autowired
  MessageFactory messageFactory;

  @Autowired
  RentalFactory rentalFactory;

  @Autowired
  UserFactory userFactory;

  /**
   * Creates a Message entity using the MessageFactory based on the provided information.
   *
   * @param messageRequest The MessageRequestModel containing information for creating the Message.
   * @return The created Message entity.
   */
  @Override
  public Message getFactoryMessageEntity(MessageRequestModel messageRequest) {
    return messageFactory.getMessageEntity(messageRequest);
  }

  /**
   * Creates a Rental entity for posting using the RentalFactory based on the provided information.
   *
   * @param userId              The ID of the user creating the rental.
   * @param imageUrl            The URL of the rental picture.
   * @param postRentalRequest   The AddRentalRequestModel containing information for creating the Rental.
   * @return The created Rental entity.
   */
  @Override
  public Rental getFactoryRentalPostEntity(
    Long userId,
    String imageUrl,
    AddRentalRequestModel postRentalRequest
  ) {
    return rentalFactory.getPostRentalEntity(
      userId,
      imageUrl,
      postRentalRequest
    );
  }

  /**
   * Creates a Rental entity for updating using the RentalFactory based on the provided information.
   *
   * @param currentRental       The current state of the rental.
   * @param putRentalRequest    The PutRentalRequestModel containing information for updating the Rental.
   * @return The updated Rental entity.
   */
  @Override
  public Rental getFactoryRentalPutEntity(
    Rental currentRental,
    PutRentalRequestModel putRentalRequest
  ) {
    return rentalFactory.getPutRentalEntity(currentRental, putRentalRequest);
  }

  /**
   * Creates a RentalsDTO using the RentalFactory based on the provided Rental entity.
   *
   * @param rental The Rental entity.
   * @return The created RentalsDTO.
   */
  @Override
  public RentalsDTO getFactoryRentalsDTO(Rental rental) {
    return rentalFactory.getRentalsDTO(rental);
  }

  /**
   * Creates a User entity for user registration using the UserFactory based on the provided information.
   *
   * @param registerRequestUser The RegisterRequestModel containing information for creating the User.
   * @return The created User entity.
   */
  @Override
  public DbUser getFactoryUserRegisterEntity(
    RegisterRequestModel registerRequestUser
  ) {
    return userFactory.getPostUserEntity(registerRequestUser);
  }

  /**
   * Creates a UserDTO using the UserFactory based on the provided User entity.
   *
   * @param user The User entity.
   * @return The created UserDTO.
   */
  @Override
  public UserDTO getFactoryUserDTO(DbUser user) {
    return userFactory.getUserDTO(user);
  }
}

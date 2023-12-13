package com.chatop.utils.EntityAndDTOCreation;

import com.chatop.chatopApiDTO.RentalsDTO;
import com.chatop.chatopApiDTO.UserDTO;
import com.chatop.chatopApiModel.DbUser;
import com.chatop.chatopApiModel.Message;
import com.chatop.chatopApiModel.Rental;
import com.chatop.utils.EntityAndDTOCreation.Factory.MessageFactory;
import com.chatop.utils.EntityAndDTOCreation.Factory.RentalFactory;
import com.chatop.utils.EntityAndDTOCreation.Factory.UserFactory;
import com.chatop.utils.Interface.EntityAndDTOCreationInterface.EntityAndDTOCreationServiceInterface;
import com.chatop.utils.ReqResModelsAndServices.Request.AddRentalRequestModel;
import com.chatop.utils.ReqResModelsAndServices.Request.MessageRequestModel;
import com.chatop.utils.ReqResModelsAndServices.Request.PutRentalRequestModel;
import com.chatop.utils.ReqResModelsAndServices.Request.RegisterRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntityAndDTOCreationService
  implements EntityAndDTOCreationServiceInterface {

  @Autowired
  MessageFactory messageFactory;

  @Autowired
  RentalFactory rentalFactory;

  @Autowired
  UserFactory userFactory;

  @Override
  public Message getFactoryMessageEntity(MessageRequestModel messageRequest) {
    return messageFactory.getMessageEntity(messageRequest);
  }

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

  @Override
  public Rental getFactoryRentalPutEntity(
    Rental currentRental,
    PutRentalRequestModel putRentalRequest
  ) {
    return rentalFactory.getPutRentalEntity(currentRental, putRentalRequest);
  }

  @Override
  public RentalsDTO getFactoryRentalsDTO(Rental rental) {
    return rentalFactory.getRentalsDTO(rental);
  }

  @Override
  public DbUser getFactoryUserRegisterEntity(
    RegisterRequestModel registerRequestUser
  ) {
    return userFactory.getPostUserEntity(registerRequestUser);
  }

  @Override
  public UserDTO getFactoryUserDTO(DbUser user) {
    return userFactory.getUserDTO(user);
  }
}

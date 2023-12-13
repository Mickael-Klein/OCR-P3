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

public interface EntityAndDTOCreationServiceInterface {
  Message getFactoryMessageEntity(MessageRequestModel messageRequest);
  Rental getFactoryRentalPostEntity(
    Long userId,
    String imageUrl,
    AddRentalRequestModel postRentalRequest
  );
  Rental getFactoryRentalPutEntity(
    Rental currentRental,
    PutRentalRequestModel putRentalRequest
  );
  RentalsDTO getFactoryRentalsDTO(Rental rental);
  DbUser getFactoryUserRegisterEntity(RegisterRequestModel registerRequestUser);
  UserDTO getFactoryUserDTO(DbUser user);
}

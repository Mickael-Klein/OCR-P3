package com.chatop.utils.Interface.EntityAndDTOCreationInterface.FactoryInterface;

import com.chatop.chatopApiDTO.RentalsDTO;
import com.chatop.chatopApiModel.Rental;
import com.chatop.utils.ReqResModelsAndServices.Request.AddRentalRequestModel;
import com.chatop.utils.ReqResModelsAndServices.Request.PutRentalRequestModel;

public interface RentalFactoryInterface {
  Rental getPostRentalEntity(
    Long userId,
    String imageUrl,
    AddRentalRequestModel postRentalRequest
  );
  Rental getPutRentalEntity(
    Rental currentRental,
    PutRentalRequestModel putRentalRequest
  );
  RentalsDTO getRentalsDTO(Rental rental);
}

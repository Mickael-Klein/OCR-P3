package com.chatop.utils.EntityAndDTOCreation.Factory;

import com.chatop.chatopApiDTO.RentalsDTO;
import com.chatop.chatopApiModel.Rental;
import com.chatop.utils.Common.UrlGeneratorService;
import com.chatop.utils.Interface.EntityAndDTOCreationInterface.FactoryInterface.RentalFactoryInterface;
import com.chatop.utils.ReqResModelsAndServices.Request.AddRentalRequestModel;
import com.chatop.utils.ReqResModelsAndServices.Request.PutRentalRequestModel;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentalFactory implements RentalFactoryInterface {

  @Autowired
  private UrlGeneratorService urlGeneratorService;

  @Override
  public Rental getPostRentalEntity(
    Long userId,
    String imageUrl,
    AddRentalRequestModel postRentalRequest
  ) {
    Rental rentalToSave = new Rental();
    rentalToSave.setName(postRentalRequest.getName());
    rentalToSave.setSurface(postRentalRequest.getSurface());
    rentalToSave.setPrice(postRentalRequest.getPrice());
    rentalToSave.setPicture(imageUrl);
    rentalToSave.setDescription(postRentalRequest.getDescription());
    rentalToSave.setCreatedAt(LocalDateTime.now());
    rentalToSave.setUpdatedAt(LocalDateTime.now());
    rentalToSave.setOwnerId(userId);

    return rentalToSave;
  }

  @Override
  public Rental getPutRentalEntity(
    Rental currentRental,
    PutRentalRequestModel putRentalRequest
  ) {
    currentRental.setName(putRentalRequest.getName());
    currentRental.setSurface(putRentalRequest.getSurface());
    currentRental.setPrice(putRentalRequest.getPrice());
    currentRental.setDescription(putRentalRequest.getDescription());
    currentRental.setUpdatedAt(LocalDateTime.now());

    return currentRental;
  }

  @Override
  public RentalsDTO getRentalsDTO(Rental rental) {
    RentalsDTO rentalsDTO = new RentalsDTO();
    rentalsDTO.setId(rental.getId());
    rentalsDTO.setName(rental.getName());
    rentalsDTO.setSurface(rental.getSurface());
    rentalsDTO.setPrice(rental.getPrice());
    rentalsDTO.setPicture(
      urlGeneratorService.getFinalClientUrl(rental.getPicture())
    );
    rentalsDTO.setDescription(rental.getDescription());
    rentalsDTO.setOwner_id(rental.getOwnerId());
    rentalsDTO.setCreated_at(rental.getCreatedAt().toLocalDate());
    rentalsDTO.setUpdated_at(rental.getUpdatedAt().toLocalDate());

    return rentalsDTO;
  }
}

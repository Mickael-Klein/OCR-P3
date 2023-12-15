package com.chatop.utils.EntityAndDTOCreation.Factory;

<<<<<<< HEAD
import com.chatop.Interface.EntityAndDTOCreationInterface.FactoryInterface.RentalFactoryInterface;
import com.chatop.chatopApiDTO.RentalsDTO;
import com.chatop.chatopApiModel.Rental;
import com.chatop.utils.Common.UrlGeneratorService;
import com.chatop.utils.ReqResModelsAndServices.Request.AddRentalRequestModel;
import com.chatop.utils.ReqResModelsAndServices.Request.PutRentalRequestModel;
=======
import com.chatop.Interface.UtilEntityAndDTOCreationInterface.FactoryInterface.RentalFactoryInterface;
import com.chatop.chatopApiDTO.RentalsDTO;
import com.chatop.chatopApiModel.Rental;
import com.chatop.utils.Common.UrlGeneratorComponent;
import com.chatop.utils.RequestInput.AddRentalRequestInput;
import com.chatop.utils.RequestInput.PutRentalRequestInput;
>>>>>>> dev
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Factory component for creating Rental entities and DTOs.
 */
@Component
public class RentalFactory implements RentalFactoryInterface {

  @Autowired
  private UrlGeneratorComponent urlGeneratorComponent;

  /**
   * Creates a Rental entity for posting based on the provided information.
   *
   * @param userId               The ID of the user creating the rental.
   * @param imageUrl             The URL of the rental picture.
   * @param postRentalRequest    The AddRentalRequestModel containing information for creating the Rental.
   * @return The created Rental entity.
   */
  @Override
  public Rental getPostRentalEntity(
    Long userId,
    String imageUrl,
    AddRentalRequestInput postRentalRequest
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

  /**
   * Creates a Rental entity for updating based on the provided information.
   *
   * @param currentRental        The current state of the rental.
   * @param putRentalRequest     The PutRentalRequestModel containing information for updating the Rental.
   * @return The updated Rental entity.
   */
  @Override
  public Rental getPutRentalEntity(
    Rental currentRental,
    PutRentalRequestInput putRentalRequest
  ) {
    currentRental.setName(putRentalRequest.getName());
    currentRental.setSurface(putRentalRequest.getSurface());
    currentRental.setPrice(putRentalRequest.getPrice());
    currentRental.setDescription(putRentalRequest.getDescription());
    currentRental.setUpdatedAt(LocalDateTime.now());

    return currentRental;
  }

  /**
   * Creates a RentalsDTO based on the provided Rental entity.
   *
   * @param rental The Rental entity.
   * @return The created RentalsDTO.
   */
  @Override
  public RentalsDTO getRentalsDTO(Rental rental) {
    RentalsDTO rentalsDTO = new RentalsDTO();
    rentalsDTO.setId(rental.getId());
    rentalsDTO.setName(rental.getName());
    rentalsDTO.setSurface(rental.getSurface());
    rentalsDTO.setPrice(rental.getPrice());
    rentalsDTO.setPicture(
      urlGeneratorComponent.getFinalClientUrl(rental.getPicture())
    );
    rentalsDTO.setDescription(rental.getDescription());
    rentalsDTO.setOwner_id(rental.getOwnerId());
    rentalsDTO.setCreated_at(rental.getCreatedAt().toLocalDate());
    rentalsDTO.setUpdated_at(rental.getUpdatedAt().toLocalDate());

    return rentalsDTO;
  }
}

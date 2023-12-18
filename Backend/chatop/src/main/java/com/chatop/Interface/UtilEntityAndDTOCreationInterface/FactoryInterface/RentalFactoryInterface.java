package com.chatop.Interface.UtilEntityAndDTOCreationInterface.FactoryInterface;

import com.chatop.chatopApiDTO.RentalsDTO;
import com.chatop.chatopApiModel.Rental;
import com.chatop.utils.RequestInput.AddRentalRequestInput;
import com.chatop.utils.RequestInput.PutRentalRequestInput;

/**
 * Interface for rental factory operations.
 */
public interface RentalFactoryInterface {
  /**
   * Creates a new rental entity for a post request.
   *
   * @param userId                The ID of the user creating the rental.
   * @param imageUrl              The URL of the rental picture.
   * @param postRentalRequest     The request model for posting a new rental.
   * @return The created rental entity.
   */
  Rental getPostRentalEntity(
    long userId,
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
  Rental getPutRentalEntity(
    Rental currentRental,
    PutRentalRequestInput putRentalRequest
  );

  /**
   * Creates a DTO (Data Transfer Object) for a rental entity.
   *
   * @param rental                The rental entity to create a DTO for.
   * @return The DTO representing the rental.
   */
  RentalsDTO getRentalsDTO(Rental rental);
}

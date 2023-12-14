package com.chatop.chatopApiService;

import com.chatop.chatopApiModel.Rental;
import com.chatop.chatopApiRepository.RentalRepository;
import com.chatop.utils.Interface.ChatopApiServiceInterface.RentalServiceInterface;
import java.util.Optional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for handling rental-related operations.
 */
@Data
@Service
public class RentalService implements RentalServiceInterface {

  @Autowired
  private RentalRepository rentalRepository;

  /**
   * Retrieves all rentals from the database.
   *
   * @return Iterable collection of all rentals.
   */
  @Override
  public Iterable<Rental> getAllRentals() {
    return rentalRepository.findAll();
  }

  /**
   * Retrieves a rental by its unique identifier.
   *
   * @param id The unique identifier of the rental.
   * @return An Optional containing the rental associated with the provided ID, or empty if not found.
   */
  @Override
  public Optional<Rental> getRentalById(final Long id) {
    return rentalRepository.findById(id);
  }

  /**
   * Saves a rental in the database.
   *
   * @param rental The rental to be saved.
   * @return The saved rental.
   */
  @Override
  public Rental saveRental(Rental rental) {
    return rentalRepository.save(rental);
  }
}

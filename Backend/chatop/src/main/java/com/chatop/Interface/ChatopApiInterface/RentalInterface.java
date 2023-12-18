package com.chatop.Interface.ChatopApiInterface;

import com.chatop.chatopApiModel.Rental;
import java.util.Optional;

/**
 * Interface for rental service operations.
 */
public interface RentalInterface {
  /**
   * Retrieves all rentals.
   *
   * @return An Iterable containing all rentals.
   */
  Iterable<Rental> getAllRentals();

  /**
   * Retrieves a rental by its ID.
   *
   * @param id The ID of the rental to retrieve.
   * @return An Optional containing the retrieved rental, or empty if not found.
   */
  Optional<Rental> getRentalById(long id);

  /**
   * Saves a rental.
   *
   * @param rental The rental to be saved.
   * @return The saved rental.
   */
  Rental saveRental(Rental rental);
}

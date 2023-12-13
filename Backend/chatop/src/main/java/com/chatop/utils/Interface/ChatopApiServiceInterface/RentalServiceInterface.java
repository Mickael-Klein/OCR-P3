package com.chatop.utils.Interface.ChatopApiServiceInterface;

import com.chatop.chatopApiModel.Rental;
import java.util.Optional;

public interface RentalServiceInterface {
  Iterable<Rental> getAllRentals();
  Optional<Rental> getRentalById(Long id);
  Rental saveRental(Rental rental);
}

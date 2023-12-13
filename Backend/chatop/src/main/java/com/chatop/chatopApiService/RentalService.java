package com.chatop.chatopApiService;

import com.chatop.chatopApiModel.Rental;
import com.chatop.chatopApiRepository.RentalRepository;
import com.chatop.utils.Interface.ChatopApiServiceInterface.RentalServiceInterface;
import java.util.Optional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class RentalService implements RentalServiceInterface {

  @Autowired
  private RentalRepository rentalRepository;

  @Override
  public Iterable<Rental> getAllRentals() {
    return rentalRepository.findAll();
  }

  @Override
  public Optional<Rental> getRentalById(final Long id) {
    return rentalRepository.findById(id);
  }

  @Override
  public Rental saveRental(Rental rental) {
    return rentalRepository.save(rental);
  }
}

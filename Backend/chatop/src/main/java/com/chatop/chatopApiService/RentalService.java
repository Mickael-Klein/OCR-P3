package com.chatop.chatopApiService;

import com.chatop.chatopApiModel.Rental;
import com.chatop.chatopApiRepository.RentalRepository;
import java.util.Optional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class RentalService {

  @Autowired
  private RentalRepository rentalRepository;

  public Iterable<Rental> getAllRentals() {
    return rentalRepository.findAll();
  }

  public Optional<Rental> getRentalById(final Long id) {
    return rentalRepository.findById(id);
  }

  public Rental saveRental(Rental rental) {
    return rentalRepository.save(rental);
  }
}

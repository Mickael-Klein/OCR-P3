package com.chatop.chatopApiController;

import com.chatop.chatopApiModel.Rental;
import com.chatop.chatopApiService.RentalService;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

  @Autowired
  private RentalService rentalService;

  @GetMapping("/")
  public ResponseEntity<Iterable<Rental>> getAllRentals() {
    Iterable<Rental> rentals = rentalService.getAllRentals();
    return new ResponseEntity<>(rentals, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getRental(@PathVariable("id") final Long id) {
    Optional<Rental> rental = rentalService.getRentalById(id);
    if (rental.isPresent()) {
      return new ResponseEntity<>(rental.get(), HttpStatus.OK);
    } else {
      Map<String, String> errorResponse = new HashMap<>();
      errorResponse.put("error", "Rental not found with id: " + id);
      return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/")
  public ResponseEntity<Object> addRental(Rental rental) {
    try {
      rentalService.saveRental(rental);
      return ResponseEntity.ok().body("{'message': 'Rental created !'}");
    } catch (Exception e) {
      return ResponseEntity
        .badRequest()
        .body("{'message' : 'Error creating rental'}");
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateRental(
    @PathVariable final Long id,
    @RequestBody Rental rental
  ) {
    try {
      Optional<Rental> r = rentalService.getRentalById(id);
      if (r.isPresent()) {
        Rental currentRental = r.get();

        String name = rental.getName();
        if (name != null) {
          currentRental.setName(name);
        }

        BigDecimal surface = rental.getSurface();
        if (surface != null) {
          currentRental.setSurface(surface);
        }

        BigDecimal price = rental.getPrice();
        if (price != null) {
          currentRental.setPrice(price);
        }

        String description = rental.getDescription();
        if (description != null) {
          currentRental.setDescription(description);
        }

        return ResponseEntity.ok().body("{'message' : 'Rental updated !'}");
      } else {
        return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body("{'message' : 'Rental not found with id: " + id + "'}");
      }
    } catch (Exception e) {
      return ResponseEntity
        .badRequest()
        .body("{'message' : 'Error updating rental'}");
    }
  }
}

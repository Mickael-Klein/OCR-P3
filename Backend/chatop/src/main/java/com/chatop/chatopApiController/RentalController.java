package com.chatop.chatopApiController;

import com.chatop.ReqResModel.Request.AddRentalRequest;
import com.chatop.chatopApiModel.Rental;
import com.chatop.chatopApiService.RentalService;
import io.jsonwebtoken.io.IOException;
import jakarta.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class RentalController {

  @Value("${upload-dir}")
  private String uploadDir;

  @Autowired
  private RentalService rentalService;

  @GetMapping("/rentals")
  public ResponseEntity<Iterable<Rental>> getAllRentals() {
    Iterable<Rental> rentals = rentalService.getAllRentals();
    return ResponseEntity.ok().body(rentals);
  }

  @GetMapping("/rentals/{id}")
  public ResponseEntity<Object> getRental(@PathVariable("id") final Long id) {
    Optional<Rental> rental = rentalService.getRentalById(id);
    if (rental.isPresent()) {
      return ResponseEntity.ok().body(rental.get());
    } else {
      return ResponseEntity
        .badRequest()
        .body("Rental not found with id: " + id);
    }
  }

  @PostMapping("/rentals")
  public ResponseEntity<Object> addRental(
    @RequestParam("name") String name,
    @RequestParam("surface") BigDecimal surface,
    @RequestParam("price") BigDecimal price,
    @RequestParam("description") String description,
    @RequestParam("picture") MultipartFile picture,
    @AuthenticationPrincipal Jwt jwt
  ) {
    try {
      String userIdFromJwtToken = jwt.getSubject();
      Long userId = Long.parseLong(userIdFromJwtToken);

      if (picture == null || picture.isEmpty()) {
        return ResponseEntity
          .badRequest()
          .body("{'message': 'The rental's picture is missing'}");
      }

      if (!isImage(picture.getBytes())) {
        return ResponseEntity
          .badRequest()
          .body("{'message': 'The rental's picture provided is not an image'}");
      }

      String pictureName =
        System.currentTimeMillis() + "_" + picture.getOriginalFilename();

      Path path = Paths.get(uploadDir, pictureName);
      picture.transferTo(path);

      String imageUrl = "/rentalPictures/" + pictureName;

      Rental rentalToSave = new Rental();
      rentalToSave.setName(name);
      rentalToSave.setSurface(surface);
      rentalToSave.setPrice(price);
      rentalToSave.setPicture(imageUrl);
      rentalToSave.setDescription(description);
      rentalToSave.setCreatedAt(LocalDateTime.now());
      rentalToSave.setUpdatedAt(LocalDateTime.now());
      rentalToSave.setOwnerId(userId);

      rentalService.saveRental(rentalToSave);
      return ResponseEntity.ok().body("{'message': 'Rental created !'}");
    } catch (Exception e) {
      return ResponseEntity
        .badRequest()
        .body("{'message' : 'Error creating rental'}");
    }
  }

  @PutMapping("/rentals/{id}")
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

  private Boolean isImage(byte[] bytes) {
    try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes)) {
      BufferedImage image = ImageIO.read(bis);
      return image != null;
    } catch (Exception e) {
      return false;
    }
  }
}

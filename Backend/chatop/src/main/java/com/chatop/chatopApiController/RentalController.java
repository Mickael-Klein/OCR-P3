package com.chatop.chatopApiController;

import com.chatop.ReqResModel.Request.AddRentalRequest;
import com.chatop.ReqResModel.Request.PutRentalRequest;
import com.chatop.chatopApiDTO.RentalDTO;
import com.chatop.chatopApiDTO.RentalsDTO;
import com.chatop.chatopApiModel.Rental;
import com.chatop.chatopApiService.RentalService;
import com.chatop.utils.DateConverterService;
import jakarta.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class RentalController {

  @Value("${upload-dir}")
  private String uploadDir;

  @Autowired
  private RentalService rentalService;

  @Autowired
  private DateConverterService dateConverterService;

  @GetMapping("/rentals")
  public ResponseEntity<Object> getAllRentals() {
    try {
      Iterable<Rental> rentals = rentalService.getAllRentals();
      List<RentalsDTO> rentalsListToSend = new ArrayList<>();
      for (Rental rental : rentals) {
        RentalsDTO rentalsDTO = new RentalsDTO();
        rentalsDTO.setId(rental.getId());
        rentalsDTO.setName(rental.getName());
        rentalsDTO.setSurface(rental.getSurface());
        rentalsDTO.setPrice(rental.getPrice());
        rentalsDTO.setPicture("http://localhost:3001" + rental.getPicture());
        rentalsDTO.setDescription(rental.getDescription());
        rentalsDTO.setOwnerId(rental.getOwnerId());

        String formattedCreatedDate = dateConverterService.convertIsoToLocalDate(
          rental.getCreatedAt()
        );
        String formattedUpdatedDate = dateConverterService.convertIsoToLocalDate(
          rental.getUpdatedAt()
        );

        rentalsDTO.setCreated_at(formattedCreatedDate);
        rentalsDTO.setUpdated_at(formattedUpdatedDate);

        rentalsListToSend.add(rentalsDTO);
      }
      Map<String, List<RentalsDTO>> responseMap = new HashMap<>();
      responseMap.put("rentals", rentalsListToSend);
      return ResponseEntity.ok().body(responseMap);
    } catch (Exception e) {
      return ResponseEntity
        .internalServerError()
        .body("{'message': 'An error occured'}");
    }
  }

  @GetMapping("/rentals/{id}")
  public ResponseEntity<Object> getRental(@PathVariable("id") final Long id) {
    try {
      Optional<Rental> optionalRental = rentalService.getRentalById(id);
      if (!optionalRental.isPresent()) {
        return ResponseEntity
          .badRequest()
          .body("{'message': 'Incorrect rental's id request parameter'}");
      }
      Rental rental = optionalRental.get();
      RentalDTO rentalDTO = new RentalDTO();
      rentalDTO.setId(rental.getId());
      rentalDTO.setName(rental.getName());
      rentalDTO.setSurface(rental.getSurface());
      rentalDTO.setPrice(rental.getPrice());

      List<String> pictureArray = new ArrayList<>();
      pictureArray.add("http://localhost:3001" + rental.getPicture());
      rentalDTO.setPicture(pictureArray);

      rentalDTO.setDescription(rental.getDescription());
      rentalDTO.setOwner_id(rental.getId());

      String formattedCreatedDate = dateConverterService.convertIsoToLocalDate(
        rental.getCreatedAt()
      );
      String formattedUpdatedDate = dateConverterService.convertIsoToLocalDate(
        rental.getUpdatedAt()
      );

      rentalDTO.setCreated_at(formattedCreatedDate);
      rentalDTO.setUpdated_at(formattedUpdatedDate);

      return ResponseEntity.ok().body(rentalDTO);
    } catch (Exception e) {
      return ResponseEntity
        .internalServerError()
        .body("{'message': 'An error occured'}");
    }
  }

  @PostMapping("/rentals")
  public ResponseEntity<Object> addRental(
    @Valid @ModelAttribute AddRentalRequest addRentalRequest,
    BindingResult bindingResult,
    @AuthenticationPrincipal Jwt jwt
  ) {
    try {
      String userIdFromJwtToken = jwt.getSubject();
      Long userId = Long.parseLong(userIdFromJwtToken);

      Boolean isRequestPayloadInvalid = bindingResult.hasErrors();
      if (isRequestPayloadInvalid) {
        return ResponseEntity
          .badRequest()
          .body("{'message': 'Incorrect rental's datas'}");
      }

      MultipartFile picture = addRentalRequest.getPicture();

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
      rentalToSave.setName(addRentalRequest.getName());
      rentalToSave.setSurface(addRentalRequest.getSurface());
      rentalToSave.setPrice(addRentalRequest.getPrice());
      rentalToSave.setPicture(imageUrl);
      rentalToSave.setDescription(addRentalRequest.getDescription());
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
    @ModelAttribute PutRentalRequest putRentalRequest,
    BindingResult bindingResult,
    @AuthenticationPrincipal Jwt jwt
  ) {
    try {
      String userIdFromJwtToken = jwt.getSubject();
      Long userId = Long.parseLong(userIdFromJwtToken);

      Boolean isRequestPayloadInvalid = bindingResult.hasErrors();
      if (isRequestPayloadInvalid) {
        return ResponseEntity
          .badRequest()
          .body("{'message': 'Incorrect rental's datas for updating'}");
      }

      Optional<Rental> optionalRental = rentalService.getRentalById(id);
      if (!optionalRental.isPresent()) {
        return ResponseEntity
          .badRequest()
          .body("{'message': 'Incorrect rental's id in request url'}");
      }

      Rental rental = optionalRental.get();

      if (rental.getOwnerId() != userId) {
        return ResponseEntity
          .badRequest()
          .body("{'message': 'User isn't authorize to modify this rental'}");
      }

      rental.setName(putRentalRequest.getName());
      rental.setSurface(putRentalRequest.getSurface());
      rental.setPrice(putRentalRequest.getPrice());
      rental.setDescription(putRentalRequest.getDescription());
      rental.setUpdatedAt(LocalDateTime.now());

      rentalService.saveRental(rental);

      return ResponseEntity.ok().body("{'message': 'Rental updated !'}");
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

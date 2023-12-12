package com.chatop.chatopApiController;

import com.chatop.chatopApiDTO.RentalsDTO;
import com.chatop.chatopApiModel.Rental;
import com.chatop.chatopApiService.RentalService;
import com.chatop.utils.ReqResModel.Request.AddRentalRequest;
import com.chatop.utils.ReqResModel.Request.PutRentalRequest;
import com.chatop.utils.ReqResModel.Response.RentalResponseService;
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
  private RentalResponseService rentalResponseService;

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
        rentalsDTO.setOwner_id(rental.getOwnerId());
        rentalsDTO.setCreated_at(rental.getCreatedAt().toLocalDate());
        rentalsDTO.setUpdated_at(rental.getUpdatedAt().toLocalDate());

        rentalsListToSend.add(rentalsDTO);
      }
      Map<String, List<RentalsDTO>> responseMap = new HashMap<>();
      responseMap.put("rentals", rentalsListToSend);
      return ResponseEntity.ok().body(responseMap);
    } catch (Exception e) {
      return ResponseEntity
        .internalServerError()
        .body(rentalResponseService.getErrorOccurJsonString());
    }
  }

  @GetMapping("/rentals/{id}")
  public ResponseEntity<Object> getRental(@PathVariable("id") final Long id) {
    try {
      Optional<Rental> optionalRental = rentalService.getRentalById(id);
      if (!optionalRental.isPresent()) {
        return ResponseEntity
          .badRequest()
          .body(
            rentalResponseService.getIncorrectRentalIdParameterJsonString()
          );
      }
      Rental rental = optionalRental.get();
      RentalsDTO rentalDTO = new RentalsDTO();
      rentalDTO.setId(rental.getId());
      rentalDTO.setName(rental.getName());
      rentalDTO.setSurface(rental.getSurface());
      rentalDTO.setPrice(rental.getPrice());
      rentalDTO.setPicture("http://localhost:3001" + rental.getPicture());
      rentalDTO.setDescription(rental.getDescription());
      rentalDTO.setOwner_id(rental.getOwnerId());
      rentalDTO.setCreated_at(rental.getCreatedAt().toLocalDate());
      rentalDTO.setUpdated_at(rental.getUpdatedAt().toLocalDate());

      return ResponseEntity.ok().body(rentalDTO);
    } catch (Exception e) {
      return ResponseEntity
        .internalServerError()
        .body(rentalResponseService.getErrorOccurJsonString());
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
          .body(rentalResponseService.getIncorrectRentalDataJsonString());
      }

      MultipartFile picture = addRentalRequest.getPicture();

      if (picture == null || picture.isEmpty()) {
        return ResponseEntity
          .badRequest()
          .body(rentalResponseService.getMissingPictureJsonString());
      }

      if (!isImage(picture.getBytes())) {
        return ResponseEntity
          .badRequest()
          .body(rentalResponseService.getNotImageJsonString());
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
      return ResponseEntity
        .ok()
        .body(rentalResponseService.getRentalCreatedJsonString());
    } catch (Exception e) {
      return ResponseEntity
        .badRequest()
        .body(rentalResponseService.getErrorCreatingRentalJsonString());
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
          .body(
            rentalResponseService.getIncorrectDataForRentalUpdateJsonString()
          );
      }

      Optional<Rental> optionalRental = rentalService.getRentalById(id);
      if (!optionalRental.isPresent()) {
        return ResponseEntity
          .badRequest()
          .body(
            rentalResponseService.getIncorrectRentalIdParameterJsonString()
          );
      }

      Rental rental = optionalRental.get();

      if (rental.getOwnerId() != userId) {
        return ResponseEntity
          .badRequest()
          .body(
            rentalResponseService.getUserNotAuthorizeForRentalUpdateJsonString()
          );
      }

      rental.setName(putRentalRequest.getName());
      rental.setSurface(putRentalRequest.getSurface());
      rental.setPrice(putRentalRequest.getPrice());
      rental.setDescription(putRentalRequest.getDescription());
      rental.setUpdatedAt(LocalDateTime.now());

      rentalService.saveRental(rental);

      return ResponseEntity
        .ok()
        .body(rentalResponseService.getRentalUpdatedJsonString());
    } catch (Exception e) {
      return ResponseEntity
        .badRequest()
        .body(rentalResponseService.getErrorOnUpdateJsonString());
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

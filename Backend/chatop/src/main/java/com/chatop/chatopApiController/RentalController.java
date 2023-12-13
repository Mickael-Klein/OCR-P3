package com.chatop.chatopApiController;

import com.chatop.chatopApiDTO.RentalsDTO;
import com.chatop.chatopApiModel.Rental;
import com.chatop.chatopApiService.JWTService;
import com.chatop.chatopApiService.RentalService;
import com.chatop.utils.Common.PictureHandlerService;
import com.chatop.utils.EntityAndDTOCreation.EntityAndDTOCreationService;
import com.chatop.utils.ReqResModelsAndServices.Request.AddRentalRequestModel;
import com.chatop.utils.ReqResModelsAndServices.Request.PutRentalRequestModel;
import com.chatop.utils.ReqResModelsAndServices.Response.RentalResponseService;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api")
public class RentalController {

  @Autowired
  private RentalService rentalService;

  @Autowired
  private RentalResponseService rentalResponseService;

  @Autowired
  private JWTService jwtService;

  @Autowired
  private PictureHandlerService pictureHandlerService;

  @Autowired
  private EntityAndDTOCreationService entityAndDTOCreationService;

  @GetMapping("/rentals")
  public ResponseEntity<Object> getAllRentals() {
    try {
      Iterable<Rental> rentals = rentalService.getAllRentals();
      List<RentalsDTO> rentalsListToSend = new ArrayList<>();
      for (Rental rental : rentals) {
        RentalsDTO rentalsDTO = entityAndDTOCreationService.getFactoryRentalsDTO(
          rental
        );
        rentalsListToSend.add(rentalsDTO);
      }

      return ResponseEntity
        .ok()
        .body(rentalResponseService.getAllRentalsMap(rentalsListToSend));
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
      RentalsDTO rentalDTO = entityAndDTOCreationService.getFactoryRentalsDTO(
        rental
      );

      return ResponseEntity.ok().body(rentalDTO);
    } catch (Exception e) {
      return ResponseEntity
        .internalServerError()
        .body(rentalResponseService.getErrorOccurJsonString());
    }
  }

  @PostMapping("/rentals")
  public ResponseEntity<Object> addRental(
    @Valid @ModelAttribute AddRentalRequestModel addRentalRequest,
    BindingResult bindingResult,
    @AuthenticationPrincipal Jwt jwt
  ) {
    try {
      Long userId = jwtService.getUserIdFromJwtLong(jwt);

      Boolean isRequestPayloadInvalid = bindingResult.hasErrors();
      if (isRequestPayloadInvalid) {
        return ResponseEntity
          .badRequest()
          .body(rentalResponseService.getIncorrectRentalDataJsonString());
      }

      Map<String, Object> pictureHandlerServiceResponse = pictureHandlerService.savePictureInServerAndReturnServerAddressOrError(
        addRentalRequest.getPicture()
      );
      Boolean isPictureHandlingSuccess = (Boolean) pictureHandlerServiceResponse.get(
        pictureHandlerService.getSuccessConstant()
      );
      if (!isPictureHandlingSuccess) {
        // Suppressing the warning because the service ensures that "error" is of type ResponseEntity
        @SuppressWarnings("unchecked")
        ResponseEntity<Object> errorResponse = (ResponseEntity<Object>) pictureHandlerServiceResponse.get(
          pictureHandlerService.getErrorConstant()
        );
        return errorResponse;
      }

      String imageUrl = (String) pictureHandlerServiceResponse.get(
        pictureHandlerService.getUrlConstant()
      );

      Rental rentalToSave = entityAndDTOCreationService.getFactoryRentalPostEntity(
        userId,
        imageUrl,
        addRentalRequest
      );

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
    @ModelAttribute PutRentalRequestModel putRentalRequest,
    BindingResult bindingResult,
    @AuthenticationPrincipal Jwt jwt
  ) {
    try {
      Long userId = jwtService.getUserIdFromJwtLong(jwt);

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

      if (!jwtService.areUserIdMatching(userId, rental.getOwnerId())) {
        return ResponseEntity
          .badRequest()
          .body(
            rentalResponseService.getUserNotAuthorizeForRentalUpdateJsonString()
          );
      }

      Rental updatedRental = entityAndDTOCreationService.getFactoryRentalPutEntity(
        rental,
        putRentalRequest
      );

      rentalService.saveRental(updatedRental);

      return ResponseEntity
        .ok()
        .body(rentalResponseService.getRentalUpdatedJsonString());
    } catch (Exception e) {
      return ResponseEntity
        .badRequest()
        .body(rentalResponseService.getErrorOnUpdateJsonString());
    }
  }
}

package com.chatop.chatopApiController;

import com.chatop.Interface.ChatopApiServiceInterface.RentalServiceInterface;
import com.chatop.Interface.UtilCommonInterface.PictureHandlerComponentInterface;
import com.chatop.Interface.UtilEntityAndDTOCreationInterface.EntityAndDTOCreationComponentInterface;
import com.chatop.Interface.UtilResponseInterface.RentalResponseComponentInterface;
import com.chatop.chatopApiDTO.RentalsDTO;
import com.chatop.chatopApiModel.Rental;
import com.chatop.chatopApiService.JWTService;
import com.chatop.utils.RequestInput.AddRentalRequestInput;
import com.chatop.utils.RequestInput.PutRentalRequestInput;
import com.chatop.utils.SwaggerApiResponse.SwaggerApiMessageResponseModel;
import com.chatop.utils.SwaggerApiResponse.SwaggerApiRentalListResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

/**
 * Controller handling rental-related operations.
 * Provides endpoints for retrieving, adding, and updating rental information.
 */
@RestController
@RequestMapping("/api")
public class RentalController {

  @Autowired
  private RentalServiceInterface rentalService;

  @Autowired
  private RentalResponseComponentInterface rentalResponseComponent;

  @Autowired
  private JWTService jwtService;

  @Autowired
  private PictureHandlerComponentInterface pictureHandlerComponent;

  @Autowired
  private EntityAndDTOCreationComponentInterface entityAndDTOCreationComponent;

  /**
   * Retrieves all rentals.
   *
   * @return ResponseEntity<Object> containing a list of RentalsDTO representing all rentals
   */
  @ApiResponses(
    value = {
      @ApiResponse(
        responseCode = "200",
        description = "Get rentals successfully",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(
              implementation = SwaggerApiRentalListResponseModel.class
            )
          ),
        }
      ),
      @ApiResponse(
        responseCode = "401",
        description = "Unauthorize",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(
              implementation = SwaggerApiMessageResponseModel.class
            )
          ),
        }
      ),
      @ApiResponse(
        responseCode = "500",
        description = "Internal Server Error",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(
              implementation = SwaggerApiMessageResponseModel.class
            )
          ),
        }
      ),
    }
  )
  @Operation(security = { @SecurityRequirement(name = "bearer-key") })
  @GetMapping("/rentals")
  public ResponseEntity<Object> getAllRentals() {
    try {
      Iterable<Rental> rentals = rentalService.getAllRentals();
      List<RentalsDTO> rentalsListToSend = new ArrayList<>();
      for (Rental rental : rentals) {
        RentalsDTO rentalsDTO = entityAndDTOCreationComponent.getFactoryRentalsDTO(
          rental
        );
        rentalsListToSend.add(rentalsDTO);
      }

      return ResponseEntity
        .ok()
        .body(rentalResponseComponent.getAllRentalsMap(rentalsListToSend));
    } catch (Exception e) {
      return ResponseEntity
        .internalServerError()
        .body(rentalResponseComponent.getErrorOccurJsonString());
    }
  }

  /**
   * Retrieves a specific rental based on the provided ID.
   *
   * @param id The ID of the rental to retrieve.
   * @return ResponseEntity<Object> containing the RentalsDTO representing the requested rental
   */
  @ApiResponses(
    value = {
      @ApiResponse(
        responseCode = "200",
        description = "Get rental by id successfully",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = RentalsDTO.class)
          ),
        }
      ),
      @ApiResponse(
        responseCode = "400",
        description = "Bad Request, rental not found",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(
              implementation = SwaggerApiMessageResponseModel.class
            )
          ),
        }
      ),
      @ApiResponse(
        responseCode = "401",
        description = "Unauthorize",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(
              implementation = SwaggerApiMessageResponseModel.class
            )
          ),
        }
      ),
      @ApiResponse(
        responseCode = "500",
        description = "Internal Server Error",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(
              implementation = SwaggerApiMessageResponseModel.class
            )
          ),
        }
      ),
    }
  )
  @Operation(security = { @SecurityRequirement(name = "bearer-key") })
  @GetMapping("/rentals/{id}")
  public ResponseEntity<Object> getRental(@PathVariable("id") final Long id) {
    try {
      Optional<Rental> optionalRental = rentalService.getRentalById(id);
      if (!optionalRental.isPresent()) {
        return ResponseEntity
          .badRequest()
          .body(
            rentalResponseComponent.getIncorrectRentalIdParameterJsonString()
          );
      }
      Rental rental = optionalRental.get();
      RentalsDTO rentalDTO = entityAndDTOCreationComponent.getFactoryRentalsDTO(
        rental
      );

      return ResponseEntity.ok().body(rentalDTO);
    } catch (Exception e) {
      return ResponseEntity
        .internalServerError()
        .body(rentalResponseComponent.getErrorOccurJsonString());
    }
  }

  /**
   * Adds a new rental based on the provided request data.
   *
   * @param addRentalRequest  The request model containing data for creating a new rental.
   * @param bindingResult     The result of the validation process for the request payload.
   * @param jwt               The authentication token obtained from the request header.
   * @return ResponseEntity<Object> containing the result of the rental creation operation
   */
  @ApiResponses(
    value = {
      @ApiResponse(
        responseCode = "200",
        description = "Rental added successfully",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(
              implementation = SwaggerApiMessageResponseModel.class
            )
          ),
        }
      ),
      @ApiResponse(
        responseCode = "400",
        description = "Bad Request,incorrect rental datas",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(
              implementation = SwaggerApiMessageResponseModel.class
            )
          ),
        }
      ),
      @ApiResponse(
        responseCode = "401",
        description = "Unauthorize",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(
              implementation = SwaggerApiMessageResponseModel.class
            )
          ),
        }
      ),
      @ApiResponse(
        responseCode = "500",
        description = "Internal Server Error",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(
              implementation = SwaggerApiMessageResponseModel.class
            )
          ),
        }
      ),
    }
  )
  @Operation(security = { @SecurityRequirement(name = "bearer-key") })
  @PostMapping("/rentals")
  public ResponseEntity<Object> addRental(
    @Valid @ModelAttribute AddRentalRequestInput addRentalRequest,
    BindingResult bindingResult,
    @AuthenticationPrincipal Jwt jwt
  ) {
    try {
      Long userId = jwtService.getUserIdFromJwtLong(jwt);

      Boolean isRequestPayloadInvalid = bindingResult.hasErrors();
      if (isRequestPayloadInvalid) {
        return ResponseEntity
          .badRequest()
          .body(rentalResponseComponent.getIncorrectRentalDataJsonString());
      }

      Map<String, Object> pictureHandlerComponentResponse = pictureHandlerComponent.savePictureInServerAndReturnServerAddressOrError(
        addRentalRequest.getPicture()
      );
      Boolean isPictureHandlingSuccess = (Boolean) pictureHandlerComponentResponse.get(
        pictureHandlerComponent.getSuccessConstant()
      );
      if (!isPictureHandlingSuccess) {
        // Suppressing the warning because the service ensures that "error" is of type ResponseEntity
        @SuppressWarnings("unchecked")
        ResponseEntity<Object> errorResponse = (ResponseEntity<Object>) pictureHandlerComponentResponse.get(
          pictureHandlerComponent.getErrorConstant()
        );
        return errorResponse;
      }

      String imageUrl = (String) pictureHandlerComponentResponse.get(
        pictureHandlerComponent.getUrlConstant()
      );

      Rental rentalToSave = entityAndDTOCreationComponent.getFactoryRentalPostEntity(
        userId,
        imageUrl,
        addRentalRequest
      );

      rentalService.saveRental(rentalToSave);
      return ResponseEntity
        .ok()
        .body(rentalResponseComponent.getRentalCreatedJsonString());
    } catch (Exception e) {
      return ResponseEntity
        .badRequest()
        .body(rentalResponseComponent.getErrorCreatingRentalJsonString());
    }
  }

  /**
   * Updates an existing rental based on the provided request data and rental ID.
   *
   * @param id                The ID of the rental to update.
   * @param putRentalRequest  The request model containing data for updating the rental.
   * @param bindingResult     The result of the validation process for the request payload.
   * @param jwt               The authentication token obtained from the request header.
   * @return ResponseEntity<Object> containing the result of the rental update operation,
   *         along with an appropriate HTTP status.
   */
  @ApiResponses(
    value = {
      @ApiResponse(
        responseCode = "200",
        description = "Rental updated successfully",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(
              implementation = SwaggerApiMessageResponseModel.class
            )
          ),
        }
      ),
      @ApiResponse(
        responseCode = "400",
        description = "Bad Request,incorrect rental datas",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(
              implementation = SwaggerApiMessageResponseModel.class
            )
          ),
        }
      ),
      @ApiResponse(
        responseCode = "401",
        description = "Unauthorize",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(
              implementation = SwaggerApiMessageResponseModel.class
            )
          ),
        }
      ),
      @ApiResponse(
        responseCode = "500",
        description = "Internal Server Error",
        content = {
          @Content(
            mediaType = "application/json",
            schema = @Schema(
              implementation = SwaggerApiMessageResponseModel.class
            )
          ),
        }
      ),
    }
  )
  @Operation(security = { @SecurityRequirement(name = "bearer-key") })
  @PutMapping("/rentals/{id}")
  public ResponseEntity<Object> updateRental(
    @PathVariable final Long id,
    @Valid @ModelAttribute PutRentalRequestInput putRentalRequest,
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
            rentalResponseComponent.getIncorrectDataForRentalUpdateJsonString()
          );
      }

      Optional<Rental> optionalRental = rentalService.getRentalById(id);
      if (!optionalRental.isPresent()) {
        return ResponseEntity
          .badRequest()
          .body(
            rentalResponseComponent.getIncorrectRentalIdParameterJsonString()
          );
      }

      Rental rental = optionalRental.get();

      if (!jwtService.areUserIdMatching(userId, rental.getOwnerId())) {
        return ResponseEntity
          .badRequest()
          .body(
            rentalResponseComponent.getUserNotAuthorizeForRentalUpdateJsonString()
          );
      }

      Rental updatedRental = entityAndDTOCreationComponent.getFactoryRentalPutEntity(
        rental,
        putRentalRequest
      );

      rentalService.saveRental(updatedRental);

      return ResponseEntity
        .ok()
        .body(rentalResponseComponent.getRentalUpdatedJsonString());
    } catch (Exception e) {
      return ResponseEntity
        .badRequest()
        .body(rentalResponseComponent.getErrorOnUpdateJsonString());
    }
  }
}

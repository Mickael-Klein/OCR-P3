package com.chatop.chatopApiController;

import com.chatop.Interface.ChatopApiInterface.MessageInterface;
import com.chatop.Interface.ChatopApiInterface.RentalInterface;
import com.chatop.Interface.UtilEntityAndDTOCreationInterface.EntityAndDTOCreationInterface;
import com.chatop.Interface.UtilResponseInterface.MessageResponseInterface;
import com.chatop.chatopApiModel.Message;
import com.chatop.chatopApiModel.Rental;
import com.chatop.chatopApiService.JWTServiceImpl;
import com.chatop.utils.RequestInput.MessageRequestInput;
import com.chatop.utils.SwaggerApiResponse.SwaggerApiMessageResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

  @Autowired
  private JWTServiceImpl jwtService;

  @Autowired
  private MessageInterface messageService;

  @Autowired
  private RentalInterface rentalService;

  @Autowired
  private MessageResponseInterface messageResponseComponent;

  @Autowired
  private EntityAndDTOCreationInterface entityAndDTOCreationComponent;

  /**
   * Handles the endpoint for sending a message.
   *
   * @param jwt             The authentication token.
   * @param request         The message request payload.
   * @param bindingResult   The result of the request payload validation.
   * @return ResponseEntity containing the result of the message sending operation.
   */
  @ApiResponses(
    value = {
      @ApiResponse(
        responseCode = "200",
        description = "Message sent successfully",
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
        description = "Invalid message request or rental ID",
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
        description = "Unauthorize user or Token id and request user id mismatch",
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
  @PostMapping("/api/messages")
  public ResponseEntity<Object> sendMessage(
    @AuthenticationPrincipal Jwt jwt,
    @Valid @RequestBody MessageRequestInput request,
    BindingResult bindingResult
  ) {
    try {
      // Check for validation errors in the request payload
      boolean isRequestPayloadInvalid = bindingResult.hasErrors();
      if (isRequestPayloadInvalid) {
        return ResponseEntity
          .badRequest()
          .body(messageResponseComponent.getInvalidMessageRequestJsonString());
      }
      // Extract user ID from the JWT token
      long userIdFromJwtToken = jwtService.getUserIdFromJwtlong(jwt);
      // Check if the user ID in the JWT token matches the one in the request
      if (
        !jwtService.areUserIdMatching(userIdFromJwtToken, request.getUser_id())
      ) {
        return ResponseEntity
          .status(HttpStatus.UNAUTHORIZED)
          .body(messageResponseComponent.getTokenMismatchingJsonString());
      }
      // Retrieve the targeted rental based on the provided rental ID
      Optional<Rental> optionalRentalTargeted = rentalService.getRentalById(
        request.getRental_id()
      );
      if (!optionalRentalTargeted.isPresent()) {
        return ResponseEntity
          .badRequest()
          .body(messageResponseComponent.getInvalidRentalIdJsonString());
      }
      // Create a Message entity from the request and save it
      Message messageToSave = entityAndDTOCreationComponent.getFactoryMessageEntity(
        request
      );
      messageService.saveMessage(messageToSave);
      // Return success response
      return ResponseEntity
        .ok()
        .body(messageResponseComponent.getMessageSentWithSuccessJsonString());
    } catch (Exception e) {
      // Handle exceptions and return an internal server error response
      return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(
          messageResponseComponent.getIntenalServerErrorMessageJsonString()
        );
    }
  }
}

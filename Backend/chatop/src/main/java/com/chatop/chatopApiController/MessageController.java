package com.chatop.chatopApiController;

import com.chatop.chatopApiModel.Message;
import com.chatop.chatopApiModel.Rental;
import com.chatop.chatopApiService.JWTService;
import com.chatop.chatopApiService.MessageService;
import com.chatop.chatopApiService.RentalService;
import com.chatop.utils.ReqResModelsAndServices.Request.MessageRequestModel;
import com.chatop.utils.ReqResModelsAndServices.Response.MessageResponseService;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
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
  private JWTService jwtService;

  @Autowired
  private MessageService messageService;

  @Autowired
  private RentalService rentalService;

  @Autowired
  private MessageResponseService messageResponseService;

  @PostMapping("/api/messages")
  public ResponseEntity<Object> sendMessage(
    @AuthenticationPrincipal Jwt jwt,
    @Valid @RequestBody MessageRequestModel request,
    BindingResult bindingResult
  ) {
    try {
      Boolean isRequestPayloadInvalid = bindingResult.hasErrors();
      if (isRequestPayloadInvalid) {
        return ResponseEntity
          .badRequest()
          .body(messageResponseService.getInvalidMessageRequestJsonString());
      }

      Long userIdFromJwtToken = jwtService.getUserIdFromJwtLong(jwt);

      if (
        !jwtService.areUserIdMatching(userIdFromJwtToken, request.getUser_id())
      ) {
        return ResponseEntity
          .status(HttpStatus.UNAUTHORIZED)
          .body(messageResponseService.getTokenMismatchingJsonString());
      }

      Optional<Rental> optionalRentalTargetted = rentalService.getRentalById(
        request.getRental_id()
      );
      if (!optionalRentalTargetted.isPresent()) {
        return ResponseEntity
          .badRequest()
          .body(messageResponseService.getInvalidRentalIdJsonString());
      }

      Message messageToSave = new Message();

      messageToSave.setMessage(request.getMessage());
      messageToSave.setUser_id(request.getUser_id());
      messageToSave.setRental_id(request.getRental_id());
      messageToSave.setCreated_at(LocalDateTime.now());
      messageToSave.setUpdated_at(LocalDateTime.now());

      messageService.saveMessage(messageToSave);
      return ResponseEntity
        .ok()
        .body(messageResponseService.getMessageSentWithSuccessJsonString());
    } catch (Exception e) {
      return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(messageResponseService.getIntenalServerErrorMessageJsonString());
    }
  }
}

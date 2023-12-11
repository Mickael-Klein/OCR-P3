package com.chatop.chatopApiController;

import com.chatop.ReqResModel.Request.MessageRequest;
import com.chatop.chatopApiModel.Message;
import com.chatop.chatopApiModel.Rental;
import com.chatop.chatopApiService.MessageService;
import com.chatop.chatopApiService.RentalService;
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
  private MessageService messageService;

  @Autowired
  private RentalService rentalService;

  @PostMapping("/api/messages")
  public ResponseEntity<Object> sendMessage(
    @AuthenticationPrincipal Jwt jwt,
    @Valid @RequestBody MessageRequest request,
    BindingResult bindingResult
  ) {
    try {
      Boolean isRequestPayloadInvalid = bindingResult.hasErrors();
      if (isRequestPayloadInvalid) {
        return ResponseEntity
          .badRequest()
          .body("{'message' : 'The Message Request is invalid'}");
      }

      String userIdFromJwtToken = jwt.getSubject();
      Long userIdFromJwtTokenLong = Long.parseLong(userIdFromJwtToken);

      System.out.println("userIdFromJwtTokenLong = " + userIdFromJwtTokenLong);
      System.out.println("request user id = " + request.getUserId());

      if (userIdFromJwtTokenLong != request.getUserId()) {
        return ResponseEntity
          .badRequest()
          .body(
            "{'message' : 'Id in token doesn't match the request user id provided'}"
          );
      }

      Optional<Rental> optionalRentalTargetted = rentalService.getRentalById(
        request.getRentalId()
      );
      if (!optionalRentalTargetted.isPresent()) {
        return ResponseEntity
          .badRequest()
          .body(
            "{'message' : 'The rental id provided doesn't match any rental in database'}"
          );
      }

      Message messageToSave = new Message();

      messageToSave.setMessage(request.getMessage());
      messageToSave.setUser_id(request.getUserId());
      messageToSave.setRental_id(request.getRentalId());
      messageToSave.setCreated_at(LocalDateTime.now());
      messageToSave.setUpdated_at(LocalDateTime.now());

      messageService.saveMessage(messageToSave);
      return ResponseEntity
        .ok()
        .body("{'message': 'Message sent with success'}");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{}");
    }
  }
}

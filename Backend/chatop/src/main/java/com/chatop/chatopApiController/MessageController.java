package com.chatop.chatopApiController;

import com.chatop.chatopApiModel.Message;
import com.chatop.chatopApiService.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

  @Autowired
  private MessageService messageService;

  @PostMapping("/api/messages")
  public ResponseEntity<Object> sendMessage(@RequestBody Message message) {
    try {
      messageService.saveMessage(message);
      return ResponseEntity
        .ok()
        .body("{'message': 'Message sent with success'}");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{}");
    }
  }
}

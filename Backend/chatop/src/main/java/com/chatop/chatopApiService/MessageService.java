package com.chatop.chatopApiService;

import com.chatop.chatopApiModel.Message;
import com.chatop.chatopApiRepository.MessageRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class MessageService {

  @Autowired
  private MessageRepository messageRepository;

  public Message saveMessage(Message message) { // Use interface instead
    System.out.println(message);
    return messageRepository.save(message);
  }
}

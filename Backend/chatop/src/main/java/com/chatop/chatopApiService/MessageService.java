package com.chatop.chatopApiService;

import com.chatop.chatopApiModel.Message;
import com.chatop.chatopApiRepository.MessageRepository;
import com.chatop.utils.Interface.ChatopApiServiceInterface.MessageServiceInterface;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class MessageService implements MessageServiceInterface {

  @Autowired
  private MessageRepository messageRepository;

  @Override
  public Message saveMessage(Message message) {
    return messageRepository.save(message);
  }
}

package com.chatop.chatopApiService;

import com.chatop.chatopApiModel.Message;
import com.chatop.chatopApiRepository.MessageRepository;
import com.chatop.utils.Interface.ChatopApiServiceInterface.MessageServiceInterface;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for handling message-related operations.
 */
@Data
@Service
public class MessageService implements MessageServiceInterface {

  @Autowired
  private MessageRepository messageRepository;

  /**
   * Saves a message in the database.
   *
   * @param message The message to be saved.
   * @return The saved message.
   */
  @Override
  public Message saveMessage(Message message) {
    return messageRepository.save(message);
  }
}

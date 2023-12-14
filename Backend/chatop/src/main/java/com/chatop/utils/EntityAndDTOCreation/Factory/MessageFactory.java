package com.chatop.utils.EntityAndDTOCreation.Factory;

import com.chatop.Interface.UtilEntityAndDTOCreationInterface.FactoryInterface.MessageFactoryInterface;
import com.chatop.chatopApiModel.Message;
import com.chatop.utils.RequestModel.MessageRequestModel;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

/**
 * Factory component for creating Message entities.
 */
@Component
public class MessageFactory implements MessageFactoryInterface {

  /**
   * Creates a Message entity based on the provided MessageRequestModel.
   *
   * @param messageRequest The MessageRequestModel containing information for creating the Message.
   * @return The created Message entity.
   */
  @Override
  public Message getMessageEntity(MessageRequestModel messageRequest) {
    Message messageEntity = new Message();
    messageEntity.setMessage(messageRequest.getMessage());
    messageEntity.setUser_id(messageRequest.getUser_id());
    messageEntity.setRental_id(messageRequest.getRental_id());
    messageEntity.setCreated_at(LocalDateTime.now());
    messageEntity.setUpdated_at(LocalDateTime.now());

    return messageEntity;
  }
}

package com.chatop.utils.EntityAndDTOCreation.Factory;

import com.chatop.chatopApiModel.Message;
import com.chatop.utils.Interface.EntityAndDTOCreationInterface.FactoryInterface.MessageFactoryInterface;
import com.chatop.utils.ReqResModelsAndServices.Request.MessageRequestModel;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class MessageFactory implements MessageFactoryInterface {

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

package com.chatop.utils.Interface.EntityAndDTOCreationInterface.FactoryInterface;

import com.chatop.chatopApiModel.Message;
import com.chatop.utils.ReqResModelsAndServices.Request.MessageRequestModel;

public interface MessageFactoryInterface {
  Message getMessageEntity(MessageRequestModel messageRequest);
}

package com.chatop.utils.Interface.EntityAndDTOCreationInterface.FactoryInterface;

import com.chatop.chatopApiModel.Message;
import com.chatop.utils.ReqResModelsAndServices.Request.MessageRequestModel;

/**
 * Interface for message factory operations.
 */
public interface MessageFactoryInterface {
  /**
   * Creates a message entity based on the provided message request model.
   *
   * @param messageRequest The message request model containing message details.
   * @return The created message entity.
   */
  Message getMessageEntity(MessageRequestModel messageRequest);
}

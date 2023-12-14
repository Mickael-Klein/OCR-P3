package com.chatop.Interface.UtilEntityAndDTOCreationInterface.FactoryInterface;

import com.chatop.chatopApiModel.Message;
import com.chatop.utils.RequestModel.MessageRequestModel;

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

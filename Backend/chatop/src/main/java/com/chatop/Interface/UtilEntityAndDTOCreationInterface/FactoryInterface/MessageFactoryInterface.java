<<<<<<<< HEAD:Backend/chatop/src/main/java/com/chatop/Interface/EntityAndDTOCreationInterface/FactoryInterface/MessageFactoryInterface.java
package com.chatop.Interface.EntityAndDTOCreationInterface.FactoryInterface;
========
package com.chatop.Interface.UtilEntityAndDTOCreationInterface.FactoryInterface;
>>>>>>>> dev:Backend/chatop/src/main/java/com/chatop/Interface/UtilEntityAndDTOCreationInterface/FactoryInterface/MessageFactoryInterface.java

import com.chatop.chatopApiModel.Message;
import com.chatop.utils.RequestInput.MessageRequestInput;

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
  Message getMessageEntity(MessageRequestInput messageRequest);
}

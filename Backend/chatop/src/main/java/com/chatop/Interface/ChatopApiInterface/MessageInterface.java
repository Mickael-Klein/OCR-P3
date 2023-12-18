package com.chatop.Interface.ChatopApiInterface;

import com.chatop.chatopApiModel.Message;

/**
 * Interface for message service operations.
 */
public interface MessageInterface {
  /**
   * Saves a message.
   *
   * @param message The message to be saved.
   * @return The saved message.
   */
  Message saveMessage(Message message);
}

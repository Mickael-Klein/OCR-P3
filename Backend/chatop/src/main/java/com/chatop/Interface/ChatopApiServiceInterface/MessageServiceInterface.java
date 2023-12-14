package com.chatop.Interface.ChatopApiServiceInterface;

import com.chatop.chatopApiModel.Message;

/**
 * Interface for message service operations.
 */
public interface MessageServiceInterface {
  /**
   * Saves a message.
   *
   * @param message The message to be saved.
   * @return The saved message.
   */
  Message saveMessage(Message message);
}

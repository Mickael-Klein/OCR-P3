package com.chatop.Interface.UtilResponseInterface;

import com.nimbusds.jose.shaded.gson.JsonObject;

/**
 * Interface for message response component operations.
 */
public interface MessageResponseComponentInterface {
  /**
   * Creates a JSON object representing a message.
   *
   * @param message The message content.
   * @return The JSON object representing the message.
   */
  JsonObject createMessageJson(String message);

  /**
   * Gets the JSON string representing an invalid message request.
   *
   * @return The JSON string representing an invalid message request.
   */
  String getInvalidMessageRequestJsonString();

  /**
   * Gets the JSON string representing a token mismatch.
   *
   * @return The JSON string representing a token mismatch.
   */
  String getTokenMismatchingJsonString();

  /**
   * Gets the JSON string representing an invalid rental ID.
   *
   * @return The JSON string representing an invalid rental ID.
   */
  String getInvalidRentalIdJsonString();

  /**
   * Gets the JSON string representing a successfully sent message.
   *
   * @return The JSON string representing a successfully sent message.
   */
  String getMessageSentWithSuccessJsonString();

  /**
   * Gets the JSON string representing an internal server error message.
   *
   * @return The JSON string representing an internal server error message.
   */
  String getIntenalServerErrorMessageJsonString();
}

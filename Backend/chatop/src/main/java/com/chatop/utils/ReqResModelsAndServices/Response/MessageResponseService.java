package com.chatop.utils.ReqResModelsAndServices.Response;

import com.chatop.Interface.ResponseInterface.MessageResponseServiceInterface;
import com.nimbusds.jose.shaded.gson.JsonObject;
import org.springframework.stereotype.Service;

/**
 * Service for handling message response related operations.
 */
@Service
public class MessageResponseService implements MessageResponseServiceInterface {

  private static final String MESSAGE_TITLE = "message";
  private static final String INVALID_MESSAGE_REQUEST =
    "The Message Request is invalid";
  private static final String TOKENS_MISMATCHING =
    "Id in token doesn't match the request user id provided";
  private static final String INVALID_RENTAL_ID =
    "The rental id provided doesn't match any rental in database";
  private static final String MESSAGE_SENT_WITH_SUCCESS =
    "Message sent with success";
  private static final String INTERNAL_SERVER_ERROR =
    "An internal error occurred while sending message";

  /**
   * Creates a JSON object with a message title.
   *
   * @param message The message content.
   * @return The JSON object with the message.
   */
  @Override
  public JsonObject createMessageJson(String message) {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty(MESSAGE_TITLE, message);
    return jsonObject;
  }

  /**
   * Retrieves the JSON string for an invalid message request.
   *
   * @return The JSON string for an invalid message request.
   */
  @Override
  public String getInvalidMessageRequestJsonString() {
    return createMessageJson(INVALID_MESSAGE_REQUEST).toString();
  }

  /**
   * Retrieves the JSON string for tokens mismatching.
   *
   * @return The JSON string for tokens mismatching.
   */
  @Override
  public String getTokenMismatchingJsonString() {
    return createMessageJson(TOKENS_MISMATCHING).toString();
  }

  /**
   * Retrieves the JSON string for an invalid rental ID.
   *
   * @return The JSON string for an invalid rental ID.
   */
  @Override
  public String getInvalidRentalIdJsonString() {
    return createMessageJson(INVALID_RENTAL_ID).toString();
  }

  /**
   * Retrieves the JSON string for a message sent with success.
   *
   * @return The JSON string for a message sent with success.
   */
  @Override
  public String getMessageSentWithSuccessJsonString() {
    return createMessageJson(MESSAGE_SENT_WITH_SUCCESS).toString();
  }

  /**
   * Retrieves the JSON string for an internal server error.
   *
   * @return The JSON string for an internal server error.
   */
  @Override
  public String getIntenalServerErrorMessageJsonString() {
    return createMessageJson(INTERNAL_SERVER_ERROR).toString();
  }
}

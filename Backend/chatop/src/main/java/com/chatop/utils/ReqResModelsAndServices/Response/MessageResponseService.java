package com.chatop.utils.ReqResModelsAndServices.Response;

import com.chatop.utils.Interface.ResponseInterface.MessageResponseServiceInterface;
import com.nimbusds.jose.shaded.gson.JsonObject;
import org.springframework.stereotype.Service;

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
    "Message send with success";
  private static final String INTERNAL_SERVER_ERROR =
    "An internal error occured while sending message";

  @Override
  public JsonObject createMessageJson(String message) {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty(MESSAGE_TITLE, message);
    return jsonObject;
  }

  @Override
  public String getInvalidMessageRequestJsonString() {
    return createMessageJson(INVALID_MESSAGE_REQUEST).toString();
  }

  @Override
  public String getTokenMismatchingJsonString() {
    return createMessageJson(TOKENS_MISMATCHING).toString();
  }

  @Override
  public String getInvalidRentalIdJsonString() {
    return createMessageJson(INVALID_RENTAL_ID).toString();
  }

  @Override
  public String getMessageSentWithSuccessJsonString() {
    return createMessageJson(MESSAGE_SENT_WITH_SUCCESS).toString();
  }

  @Override
  public String getIntenalServerErrorMessageJsonString() {
    return createMessageJson(INTERNAL_SERVER_ERROR).toString();
  }
}

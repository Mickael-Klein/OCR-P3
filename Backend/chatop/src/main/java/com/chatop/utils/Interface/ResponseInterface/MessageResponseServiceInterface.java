package com.chatop.utils.Interface.ResponseInterface;

import com.nimbusds.jose.shaded.gson.JsonObject;

public interface MessageResponseServiceInterface {
  JsonObject createMessageJson(String message);
  String getInvalidMessageRequestJsonString();
  String getTokenMismatchingJsonString();
  String getInvalidRentalIdJsonString();
  String getMessageSentWithSuccessJsonString();
  String getIntenalServerErrorMessageJsonString();
}

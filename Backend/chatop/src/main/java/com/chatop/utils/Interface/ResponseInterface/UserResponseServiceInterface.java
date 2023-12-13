package com.chatop.utils.Interface.ResponseInterface;

import com.nimbusds.jose.shaded.gson.JsonObject;

public interface UserResponseServiceInterface {
  JsonObject createMessageJson(String message);
  String getRegisteringBadCredentialsJsonString();
  String getRegisteringEmailAlreadyUsedJsonString();
  String getRegisteringErrorJsonString();
  String getLoginBadCredentialsJsonString();
  String getLoginErrorJsonString();
  String getJwtInvalidJwtJsonString();
  String getJwtTokenJsonString(String jwtToken);
  String getUserInvalidIdParameterJsonString();
  String getUserErrorOccurJsonString();
}

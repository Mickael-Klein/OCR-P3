package com.chatop.utils.ReqResModelsAndServices.Response;

import com.chatop.utils.Interface.ResponseInterface.UserResponseServiceInterface;
import com.nimbusds.jose.shaded.gson.JsonObject;
import org.springframework.stereotype.Service;

@Service
public class UserResponseService implements UserResponseServiceInterface {

  private static final String MESSAGE_TITLE = "message";
  private static final String REGISTERING_BAD_CREDENTIALS =
    "Bad credentials for registering";
  private static final String REGISTERING_EMAIL_ALREADY_USE =
    "Email is already registered";
  private static final String REGISTERING_ERROR_REGISTERING_USER =
    "An error occurred during user's registration";
  private static final String LOGIN_BAD_CREDENTIALS =
    "Bad credentials for login";
  private static final String LOGIN_ERROR_LOGIN_USER =
    "An error occurred trying to connect user";
  private static final String JWT_INVALID_JWT = "Invalid jwt";
  private static final String TOKEN = "token";
  private static final String USER_INVALID_ID_PARAMETER =
    "Invalid user id parameter";
  private static final String USER_ERROR_OCCUR =
    "An error occured trying to get user";

  @Override
  public JsonObject createMessageJson(String message) {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty(MESSAGE_TITLE, message);
    return jsonObject;
  }

  @Override
  public String getRegisteringBadCredentialsJsonString() {
    return createMessageJson(REGISTERING_BAD_CREDENTIALS).toString();
  }

  @Override
  public String getRegisteringEmailAlreadyUsedJsonString() {
    return createMessageJson(REGISTERING_EMAIL_ALREADY_USE).toString();
  }

  @Override
  public String getRegisteringErrorJsonString() {
    return createMessageJson(REGISTERING_ERROR_REGISTERING_USER).toString();
  }

  @Override
  public String getLoginBadCredentialsJsonString() {
    return createMessageJson(LOGIN_BAD_CREDENTIALS).toString();
  }

  @Override
  public String getLoginErrorJsonString() {
    return createMessageJson(LOGIN_ERROR_LOGIN_USER).toString();
  }

  @Override
  public String getJwtInvalidJwtJsonString() {
    return createMessageJson(JWT_INVALID_JWT).toString();
  }

  @Override
  public String getJwtTokenJsonString(String jwtToken) {
    JsonObject jwtTokenJson = new JsonObject();
    jwtTokenJson.addProperty(TOKEN, jwtToken);
    return jwtTokenJson.toString();
  }

  @Override
  public String getUserInvalidIdParameterJsonString() {
    return createMessageJson(USER_INVALID_ID_PARAMETER).toString();
  }

  @Override
  public String getUserErrorOccurJsonString() {
    return createMessageJson(USER_ERROR_OCCUR).toString();
  }
}
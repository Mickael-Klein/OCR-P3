package com.chatop.utils.ResponseService;

import com.chatop.Interface.UtilResponseInterface.UserResponseServiceInterface;
import com.nimbusds.jose.shaded.gson.JsonObject;
import org.springframework.stereotype.Service;

/**
 * Service for handling user response related operations.
 */
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
    "An error occurred trying to get user";

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
   * Retrieves the JSON string for bad credentials during user registration.
   *
   * @return The JSON string for bad credentials during user registration.
   */
  @Override
  public String getRegisteringBadCredentialsJsonString() {
    return createMessageJson(REGISTERING_BAD_CREDENTIALS).toString();
  }

  /**
   * Retrieves the JSON string for an email that is already registered.
   *
   * @return The JSON string for an email that is already registered.
   */
  @Override
  public String getRegisteringEmailAlreadyUsedJsonString() {
    return createMessageJson(REGISTERING_EMAIL_ALREADY_USE).toString();
  }

  /**
   * Retrieves the JSON string for an error during user registration.
   *
   * @return The JSON string for an error during user registration.
   */
  @Override
  public String getRegisteringErrorJsonString() {
    return createMessageJson(REGISTERING_ERROR_REGISTERING_USER).toString();
  }

  /**
   * Retrieves the JSON string for bad credentials during user login.
   *
   * @return The JSON string for bad credentials during user login.
   */
  @Override
  public String getLoginBadCredentialsJsonString() {
    return createMessageJson(LOGIN_BAD_CREDENTIALS).toString();
  }

  /**
   * Retrieves the JSON string for an error during user login.
   *
   * @return The JSON string for an error during user login.
   */
  @Override
  public String getLoginErrorJsonString() {
    return createMessageJson(LOGIN_ERROR_LOGIN_USER).toString();
  }

  /**
   * Retrieves the JSON string for an invalid JWT.
   *
   * @return The JSON string for an invalid JWT.
   */
  @Override
  public String getJwtInvalidJwtJsonString() {
    return createMessageJson(JWT_INVALID_JWT).toString();
  }

  /**
   * Retrieves the JSON string for a JWT token.
   *
   * @param jwtToken The JWT token.
   * @return The JSON string for the JWT token.
   */
  @Override
  public String getJwtTokenJsonString(String jwtToken) {
    JsonObject jwtTokenJson = new JsonObject();
    jwtTokenJson.addProperty(TOKEN, jwtToken);
    return jwtTokenJson.toString();
  }

  /**
   * Retrieves the JSON string for an invalid user ID parameter.
   *
   * @return The JSON string for an invalid user ID parameter.
   */
  @Override
  public String getUserInvalidIdParameterJsonString() {
    return createMessageJson(USER_INVALID_ID_PARAMETER).toString();
  }

  /**
   * Retrieves the JSON string for an error trying to get user information.
   *
   * @return The JSON string for an error trying to get user information.
   */
  @Override
  public String getUserErrorOccurJsonString() {
    return createMessageJson(USER_ERROR_OCCUR).toString();
  }
}

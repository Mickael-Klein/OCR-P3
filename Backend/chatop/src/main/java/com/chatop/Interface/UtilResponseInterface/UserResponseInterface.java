package com.chatop.Interface.UtilResponseInterface;

import com.nimbusds.jose.shaded.gson.JsonObject;

/**
 * Interface for user response component operations.
 */
public interface UserResponseInterface {
  /**
   * Creates a JSON object representing a message.
   *
   * @param message The message content.
   * @return The JSON object representing the message.
   */
  JsonObject createMessageJson(String message);

  /**
   * Gets the JSON string representing bad credentials during user registration.
   *
   * @return The JSON string representing bad credentials during user registration.
   */
  String getRegisteringBadCredentialsJsonString();

  /**
   * Gets the JSON string representing email already used during user registration.
   *
   * @return The JSON string representing email already used during user registration.
   */
  String getRegisteringEmailAlreadyUsedJsonString();

  /**
   * Gets the JSON string representing an error during user registration.
   *
   * @return The JSON string representing an error during user registration.
   */
  String getRegisteringErrorJsonString();

  /**
   * Gets the JSON string representing bad credentials during user login.
   *
   * @return The JSON string representing bad credentials during user login.
   */
  String getLoginBadCredentialsJsonString();

  /**
   * Gets the JSON string representing an error during user login.
   *
   * @return The JSON string representing an error during user login.
   */
  String getLoginErrorJsonString();

  /**
   * Gets the JSON string representing an invalid JWT (JSON Web Token).
   *
   * @return The JSON string representing an invalid JWT.
   */
  String getJwtInvalidJwtJsonString();

  /**
   * Gets the JSON string representing a JWT token.
   *
   * @param jwtToken The JWT token.
   * @return The JSON string representing a JWT token.
   */
  String getJwtTokenJsonString(String jwtToken);

  /**
   * Gets the JSON string representing an invalid user ID parameter.
   *
   * @return The JSON string representing an invalid user ID parameter.
   */
  String getUserInvalidIdParameterJsonString();

  /**
   * Gets the JSON string representing an error occurrence related to a user.
   *
   * @return The JSON string representing an error occurrence related to a user.
   */
  String getUserErrorOccurJsonString();
}

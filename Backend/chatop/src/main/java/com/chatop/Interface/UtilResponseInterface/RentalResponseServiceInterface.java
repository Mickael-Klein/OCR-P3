package com.chatop.Interface.UtilResponseInterface;

import com.chatop.chatopApiDTO.RentalsDTO;
import com.nimbusds.jose.shaded.gson.JsonObject;
import java.util.List;
import java.util.Map;

/**
 * Interface for rental response service operations.
 */
public interface RentalResponseServiceInterface {
  /**
   * Creates a JSON object representing a message.
   *
   * @param message The message content.
   * @return The JSON object representing the message.
   */
  JsonObject createMessageJson(String message);

  /**
   * Gets the JSON string representing an error occurrence.
   *
   * @return The JSON string representing an error occurrence.
   */
  String getErrorOccurJsonString();

  /**
   * Gets the JSON string representing an incorrect rental ID parameter.
   *
   * @return The JSON string representing an incorrect rental ID parameter.
   */
  String getIncorrectRentalIdParameterJsonString();

  /**
   * Gets the JSON string representing incorrect rental data.
   *
   * @return The JSON string representing incorrect rental data.
   */
  String getIncorrectRentalDataJsonString();

  /**
   * Gets the JSON string representing a missing picture.
   *
   * @return The JSON string representing a missing picture.
   */
  String getMissingPictureJsonString();

  /**
   * Gets the JSON string representing a non-image file.
   *
   * @return The JSON string representing a non-image file.
   */
  String getNotImageJsonString();

  /**
   * Gets the JSON string representing a successfully created rental.
   *
   * @return The JSON string representing a successfully created rental.
   */
  String getRentalCreatedJsonString();

  /**
   * Gets the JSON string representing an error during rental creation.
   *
   * @return The JSON string representing an error during rental creation.
   */
  String getErrorCreatingRentalJsonString();

  /**
   * Gets the JSON string representing incorrect data for rental update.
   *
   * @return The JSON string representing incorrect data for rental update.
   */
  String getIncorrectDataForRentalUpdateJsonString();

  /**
   * Gets the JSON string representing a user not authorized for rental update.
   *
   * @return The JSON string representing a user not authorized for rental update.
   */
  String getUserNotAuthorizeForRentalUpdateJsonString();

  /**
   * Gets the JSON string representing a successfully updated rental.
   *
   * @return The JSON string representing a successfully updated rental.
   */
  String getRentalUpdatedJsonString();

  /**
   * Gets the JSON string representing an error during rental update.
   *
   * @return The JSON string representing an error during rental update.
   */
  String getErrorOnUpdateJsonString();

  /**
   * Creates a map with rental information for response.
   *
   * @param rentalsDTOList The list of RentalsDTO objects.
   * @return A map containing rental information.
   */
  Map<String, List<RentalsDTO>> getAllRentalsMap(
    List<RentalsDTO> rentalsDTOList
  );
}

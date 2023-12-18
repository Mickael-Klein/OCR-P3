package com.chatop.utils.ResponseComponent;

import com.chatop.Interface.UtilResponseInterface.RentalResponseInterface;
import com.chatop.chatopApiDTO.RentalsDTO;
import com.nimbusds.jose.shaded.gson.JsonObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * Component for handling rental response related operations.
 */
@Component
public class RentalResponseImpl implements RentalResponseInterface {

  private static final String MESSAGE_TITLE = "message";
  private static final String RENTALS_TITLE = "rentals";
  private static final String ERROR_OCCUR = "An internal server error occurred";
  private static final String INCORRECT_RENTAL_ID_PARAMETER =
    "Incorrect rental's id request parameter";
  private static final String INCORRECT_RENTAL_DATA = "Incorrect rental data";
  private static final String MISSING_PICTURE = "Missing rental's picture";
  private static final String NOT_IMAGE =
    "The rental's picture provided is not an image file";
  private static final String RENTAL_CREATED = "Rental created";
  private static final String ERROR_CREATING_RENTAL = "Rental creation failed";
  private static final String INCORRECT_DATA_FOR_RENTAL_UPDATE =
    "Incorrect rental data for updating";
  private static final String USER_NOT_AUTHORIZE_FOR_THIS_RENTAL_UPDATE =
    "User isn't authorized to modify this rental";
  private static final String RENTAL_UPDATED = "Rental updated!";
  private static final String ERROR_ON_UPDATE = "Error updating rental";

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
   * Retrieves the JSON string for an internal server error.
   *
   * @return The JSON string for an internal server error.
   */
  @Override
  public String getErrorOccurJsonString() {
    return createMessageJson(ERROR_OCCUR).toString();
  }

  /**
   * Retrieves the JSON string for incorrect rental ID parameter.
   *
   * @return The JSON string for incorrect rental ID parameter.
   */
  @Override
  public String getIncorrectRentalIdParameterJsonString() {
    return createMessageJson(INCORRECT_RENTAL_ID_PARAMETER).toString();
  }

  /**
   * Retrieves the JSON string for incorrect rental data.
   *
   * @return The JSON string for incorrect rental data.
   */
  @Override
  public String getIncorrectRentalDataJsonString() {
    return createMessageJson(INCORRECT_RENTAL_DATA).toString();
  }

  /**
   * Retrieves the JSON string for missing rental's picture.
   *
   * @return The JSON string for missing rental's picture.
   */
  @Override
  public String getMissingPictureJsonString() {
    return createMessageJson(MISSING_PICTURE).toString();
  }

  /**
   * Retrieves the JSON string for the rental's picture not being an image file.
   *
   * @return The JSON string for the rental's picture not being an image file.
   */
  @Override
  public String getNotImageJsonString() {
    return createMessageJson(NOT_IMAGE).toString();
  }

  /**
   * Retrieves the JSON string for a rental created successfully.
   *
   * @return The JSON string for a rental created successfully.
   */
  @Override
  public String getRentalCreatedJsonString() {
    return createMessageJson(RENTAL_CREATED).toString();
  }

  /**
   * Retrieves the JSON string for an error during rental creation.
   *
   * @return The JSON string for an error during rental creation.
   */
  @Override
  public String getErrorCreatingRentalJsonString() {
    return createMessageJson(ERROR_CREATING_RENTAL).toString();
  }

  /**
   * Retrieves the JSON string for incorrect rental data during an update.
   *
   * @return The JSON string for incorrect rental data during an update.
   */
  @Override
  public String getIncorrectDataForRentalUpdateJsonString() {
    return createMessageJson(INCORRECT_DATA_FOR_RENTAL_UPDATE).toString();
  }

  /**
   * Retrieves the JSON string for a user not being authorized to update the rental.
   *
   * @return The JSON string for a user not being authorized to update the rental.
   */
  @Override
  public String getUserNotAuthorizeForRentalUpdateJsonString() {
    return createMessageJson(USER_NOT_AUTHORIZE_FOR_THIS_RENTAL_UPDATE)
      .toString();
  }

  /**
   * Retrieves the JSON string for a rental updated successfully.
   *
   * @return The JSON string for a rental updated successfully.
   */
  @Override
  public String getRentalUpdatedJsonString() {
    return createMessageJson(RENTAL_UPDATED).toString();
  }

  /**
   * Retrieves the JSON string for an error during rental update.
   *
   * @return The JSON string for an error during rental update.
   */
  @Override
  public String getErrorOnUpdateJsonString() {
    return createMessageJson(ERROR_ON_UPDATE).toString();
  }

  /**
   * Retrieves a map with the rentals title and a list of rentals DTOs.
   *
   * @param rentalsDTOList The list of RentalsDTO.
   * @return The map containing the rentals title and the list of rentals DTOs.
   */
  @Override
  public Map<String, List<RentalsDTO>> getAllRentalsMap(
    List<RentalsDTO> rentalsDTOList
  ) {
    Map<String, List<RentalsDTO>> responseMap = new HashMap<>();
    responseMap.put(RENTALS_TITLE, rentalsDTOList);
    return responseMap;
  }
}

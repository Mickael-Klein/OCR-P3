package com.chatop.utils.ReqResModel.Response;

import com.nimbusds.jose.shaded.gson.JsonObject;
import org.springframework.stereotype.Service;

@Service
public class RentalResponseService {

  private static final String MESSAGE_TITLE = "message";
  private static final String ERROR_OCCURE = "An error occurred";
  private static final String INCORRECT_RENTAL_ID_PARAMETER =
    "Incorrect rental's id request parameter";
  private static final String INCORRECT_RENTAL_DATA = "Incorrect rental datas";
  private static final String MISSING_PICTURE = "Missing rental's picture";
  private static final String NOT_IMAGE =
    "The rental's picture provided is not an image file";
  private static final String RENTAL_CREATED = "Rental created";
  private static final String ERROR_CREATING_RENTAL = "Rental creation failed";
  private static final String INCORRECT_DATA_FOR_RENTAL_UPDATE =
    "Incorrect rental's datas for updating";
  private static final String USER_NOT_AUTHORIZE_FOR_THIS_RENTAL_UPDATE =
    "User isn't authorized to modify this rental";
  private static final String RENTAL_UPDATED = "Rental updated !";
  private static final String ERROR_ON_UPDATE = "Error updating rental";

  public JsonObject createMessageJson(String message) {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty(MESSAGE_TITLE, message);
    return jsonObject;
  }

  public String getErrorOccurJsonString() {
    return createMessageJson(ERROR_OCCURE).toString();
  }

  public String getIncorrectRentalIdParameterJsonString() {
    return createMessageJson(INCORRECT_RENTAL_ID_PARAMETER).toString();
  }

  public String getIncorrectRentalDataJsonString() {
    return createMessageJson(INCORRECT_RENTAL_DATA).toString();
  }

  public String getMissingPictureJsonString() {
    return createMessageJson(MISSING_PICTURE).toString();
  }

  public String getNotImageJsonString() {
    return createMessageJson(NOT_IMAGE).toString();
  }

  public String getRentalCreatedJsonString() {
    return createMessageJson(RENTAL_CREATED).toString();
  }

  public String getErrorCreatingRentalJsonString() {
    return createMessageJson(ERROR_CREATING_RENTAL).toString();
  }

  public String getIncorrectDataForRentalUpdateJsonString() {
    return createMessageJson(INCORRECT_DATA_FOR_RENTAL_UPDATE).toString();
  }

  public String getUserNotAuthorizeForRentalUpdateJsonString() {
    return createMessageJson(USER_NOT_AUTHORIZE_FOR_THIS_RENTAL_UPDATE)
      .toString();
  }

  public String getRentalUpdatedJsonString() {
    return createMessageJson(RENTAL_UPDATED).toString();
  }

  public String getErrorOnUpdateJsonString() {
    return createMessageJson(ERROR_ON_UPDATE).toString();
  }
}

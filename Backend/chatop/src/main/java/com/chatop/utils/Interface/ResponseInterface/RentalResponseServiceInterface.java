package com.chatop.utils.Interface.ResponseInterface;

import com.chatop.chatopApiDTO.RentalsDTO;
import com.nimbusds.jose.shaded.gson.JsonObject;
import java.util.List;
import java.util.Map;

public interface RentalResponseServiceInterface {
  JsonObject createMessageJson(String message);
  String getErrorOccurJsonString();
  String getIncorrectRentalIdParameterJsonString();
  String getIncorrectRentalDataJsonString();
  String getMissingPictureJsonString();
  String getNotImageJsonString();
  String getRentalCreatedJsonString();
  String getErrorCreatingRentalJsonString();
  String getIncorrectDataForRentalUpdateJsonString();
  String getUserNotAuthorizeForRentalUpdateJsonString();
  String getRentalUpdatedJsonString();
  String getErrorOnUpdateJsonString();
  Map<String, List<RentalsDTO>> getAllRentalsMap(
    List<RentalsDTO> rentalsDTOList
  );
}

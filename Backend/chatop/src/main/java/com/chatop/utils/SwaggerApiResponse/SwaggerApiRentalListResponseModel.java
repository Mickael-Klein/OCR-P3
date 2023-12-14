package com.chatop.utils.SwaggerApiResponse;

import com.chatop.chatopApiDTO.RentalsDTO;
import java.util.List;
import lombok.Data;

/**
 * Represents the Rental List Response in the Swagger API.
 */
@Data
public class SwaggerApiRentalListResponseModel {

  /**
   * The list of Rental DTOs.
   */
  private List<RentalsDTO> rentals;
}

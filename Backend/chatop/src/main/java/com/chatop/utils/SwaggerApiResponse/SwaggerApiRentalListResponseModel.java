package com.chatop.utils.SwaggerApiResponse;

import com.chatop.chatopApiDTO.RentalsDTO;
import java.util.List;
import lombok.Data;

@Data
public class SwaggerApiRentalListResponseModel {

  private List<RentalsDTO> rentals;
}

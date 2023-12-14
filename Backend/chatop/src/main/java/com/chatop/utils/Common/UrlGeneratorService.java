package com.chatop.utils.Common;

import com.chatop.utils.Interface.CommonInterface.UrlGeneratorServiceInterface;
import org.springframework.stereotype.Service;

/**
 * Service for generating URLs.
 */
@Service
public class UrlGeneratorService implements UrlGeneratorServiceInterface {

  private static final String BASE_URL = "http://localhost:3001";

  /**
   * Generates the final client URL by combining the base URL and the provided address.
   *
   * @param address The address to append to the base URL.
   * @return The final client URL.
   */
  @Override
  public String getFinalClientUrl(String address) {
    String finalUrl = BASE_URL + address;
    return finalUrl;
  }
}

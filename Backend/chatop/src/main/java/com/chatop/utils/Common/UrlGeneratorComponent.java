package com.chatop.utils.Common;

import com.chatop.Interface.UtilCommonInterface.UrlGeneratorComponentInterface;
import org.springframework.stereotype.Component;

/**
 * Component for generating URLs.
 */
@Component
public class UrlGeneratorComponent implements UrlGeneratorComponentInterface {

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

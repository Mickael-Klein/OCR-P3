package com.chatop.utils.Common;

import com.chatop.Interface.UtilCommonInterface.UrlGeneratorComponentInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Component for generating URLs.
 */
@Component
public class UrlGeneratorComponent implements UrlGeneratorComponentInterface {

  @Value("${server.port}")
  private String serverPort;

  /**
   * Generates the final client URL by combining the base URL and the provided address.
   *
   * @param address The address to append to the base URL.
   * @return The final client URL.
   */
  @Override
  public String getFinalClientUrl(String address) {
    // Build the base URL using the injected server port
    String baseUrl = "http://localhost:" + serverPort;

    // Combine the base URL and the provided address
    String finalUrl = baseUrl + address;

    return finalUrl;
  }
}

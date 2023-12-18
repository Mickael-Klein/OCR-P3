package com.chatop.Interface.UtilCommonInterface;

/**
 * Interface for URL generation component operations.
 */
public interface UrlGeneratorInterface {
  /**
   * Generates the final client URL based on the provided address.
   *
   * @param address The address to be appended to the base URL.
   * @return The final client URL.
   */
  String getFinalClientUrl(String address);
}

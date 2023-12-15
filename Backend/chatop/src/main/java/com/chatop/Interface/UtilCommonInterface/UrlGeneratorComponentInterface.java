<<<<<<<< HEAD:Backend/chatop/src/main/java/com/chatop/Interface/CommonInterface/UrlGeneratorServiceInterface.java
package com.chatop.Interface.CommonInterface;
========
package com.chatop.Interface.UtilCommonInterface;
>>>>>>>> dev:Backend/chatop/src/main/java/com/chatop/Interface/UtilCommonInterface/UrlGeneratorComponentInterface.java

/**
 * Interface for URL generation component operations.
 */
public interface UrlGeneratorComponentInterface {
  /**
   * Generates the final client URL based on the provided address.
   *
   * @param address The address to be appended to the base URL.
   * @return The final client URL.
   */
  String getFinalClientUrl(String address);
}

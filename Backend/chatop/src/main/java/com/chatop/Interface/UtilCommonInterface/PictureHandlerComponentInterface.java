<<<<<<<< HEAD:Backend/chatop/src/main/java/com/chatop/Interface/CommonInterface/PictureHandlerServiceInterface.java
package com.chatop.Interface.CommonInterface;
========
package com.chatop.Interface.UtilCommonInterface;
>>>>>>>> dev:Backend/chatop/src/main/java/com/chatop/Interface/UtilCommonInterface/PictureHandlerComponentInterface.java

import java.io.IOException;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 * Interface for picture handling component operations.
 */
public interface PictureHandlerComponentInterface {
  /**
   * Gets the constant representing a successful operation.
   *
   * @return The success constant.
   */
  String getSuccessConstant();

  /**
   * Gets the constant representing a URL.
   *
   * @return The URL constant.
   */
  String getUrlConstant();

  /**
   * Gets the constant representing an error.
   *
   * @return The error constant.
   */
  String getErrorConstant();

  /**
   * Generates a unique picture name based on the original picture name.
   *
   * @param pictureName The original picture name.
   * @return The generated unique picture name.
   */
  String generatePictureName(String pictureName);

  /**
   * Generates the server address for a picture based on its name.
   *
   * @param pictureName The picture name.
   * @return The generated server address for the picture.
   */
  String generatePictureServerAddress(String pictureName);

  /**
   * Gets the server address for a picture based on its name.
   *
   * @param pictureName The picture name.
   * @return The server address for the picture.
   */
  String getPictureServerAddress(String pictureName);

  /**
   * Saves a picture in the server and returns the server address or an error.
   *
   * @param picture The picture to be saved.
   * @return A map containing the result of the picture saving operation.
   * @throws IOException If an I/O error occurs during the picture saving process.
   */
  Map<String, Object> savePictureInServerAndReturnServerAddressOrError(
    MultipartFile picture
  ) throws IOException;
}

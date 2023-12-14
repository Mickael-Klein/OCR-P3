package com.chatop.utils.Common;

import com.chatop.Interface.UtilCommonInterface.PictureHandlerComponentInterface;
import com.chatop.utils.ResponseComponent.RentalResponseComponent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Component for handling picture-related operations.
 */
@Component
public class PictureHandlerComponent
  implements PictureHandlerComponentInterface {

  @Value("${upload-dir}")
  private String uploadDir;

  @Autowired
  private RentalResponseComponent rentalResponseComponent;

  private static final String SUCCESS = "success";
  private static final String URL = "url";
  private static final String ERROR = "error";
  private static final String RENTAL_PICTURES_DIRECTORY = "/rentalPictures/";

  /**
   * Gets the success constant.
   *
   * @return The success constant.
   */
  @Override
  public String getSuccessConstant() {
    return SUCCESS;
  }

  /**
   * Gets the URL constant.
   *
   * @return The URL constant.
   */
  @Override
  public String getUrlConstant() {
    return URL;
  }

  /**
   * Gets the error constant.
   *
   * @return The error constant.
   */
  @Override
  public String getErrorConstant() {
    return ERROR;
  }

  /**
   * Generates a unique picture name based on the original filename and the current timestamp.
   *
   * @param pictureName The original filename of the picture.
   * @return The generated unique picture name.
   */
  @Override
  public String generatePictureName(String pictureName) {
    String name = System.currentTimeMillis() + "_" + pictureName;
    return name;
  }

  /**
   * Generates the server address for a picture based on its name.
   *
   * @param pictureName The name of the picture.
   * @return The generated server address for the picture.
   */
  @Override
  public String generatePictureServerAddress(String pictureName) {
    String url = RENTAL_PICTURES_DIRECTORY + pictureName;
    return url;
  }

  /**
   * Gets the server address for a picture based on its name.
   *
   * @param pictureName The name of the picture.
   * @return The server address for the picture.
   */
  @Override
  public String getPictureServerAddress(String pictureName) {
    String fullPictureName = generatePictureName(pictureName);
    return generatePictureServerAddress(fullPictureName);
  }

  /**
   * Checks if the provided bytes represent an image.
   *
   * @param bytes The byte array representing the image.
   * @return True if the bytes represent an image, false otherwise.
   */
  private Boolean isImage(byte[] bytes) {
    try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes)) {
      BufferedImage image = ImageIO.read(bis);
      return image != null;
    } catch (IOException e) {
      return false;
    }
  }

  /**
   * Saves a picture in the server and returns the server address or an error response.
   *
   * @param picture The MultipartFile representing the picture to be saved.
   * @return A Map containing the success status, server URL, or error response.
   * @throws IOException If an I/O error occurs during picture handling.
   */
  @Override
  public Map<String, Object> savePictureInServerAndReturnServerAddressOrError(
    MultipartFile picture
  ) throws IOException {
    Map<String, Object> response = new HashMap<>();

    try {
      if (picture == null || picture.isEmpty()) {
        response.put(SUCCESS, false);
        response.put(
          ERROR,
          ResponseEntity
            .badRequest()
            .body(rentalResponseComponent.getMissingPictureJsonString())
        );
        return response;
      }

      if (!isImage(picture.getBytes())) {
        response.put(SUCCESS, false);
        response.put(
          ERROR,
          ResponseEntity
            .badRequest()
            .body(rentalResponseComponent.getNotImageJsonString())
        );
        return response;
      }

      String pictureName = generatePictureName(picture.getOriginalFilename());

      Path path = Paths.get(uploadDir, pictureName);
      picture.transferTo(path);

      String imageUrl = generatePictureServerAddress(pictureName);

      response.put(SUCCESS, true);
      response.put(URL, imageUrl);
    } catch (IOException e) {
      response.put(SUCCESS, false);
      response.put(
        ERROR,
        ResponseEntity
          .internalServerError()
          .body(rentalResponseComponent.getErrorOccurJsonString())
      );
      throw e;
    }

    return response;
  }
}

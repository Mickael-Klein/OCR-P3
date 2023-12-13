package com.chatop.utils.Common;

import com.chatop.utils.ReqResModelsAndServices.Response.RentalResponseService;
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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PictureHandlerService {

  @Value("${upload-dir}")
  private String uploadDir;

  @Autowired
  private RentalResponseService rentalResponseService;

  private static final String SUCCESS = "success";
  private static final String URL = "url";
  private static final String ERROR = "error";
  private static final String RENTAL_PICTURES_DIRECTORY = "/rentalPictures/";

  public String getSuccessConstant() {
    return SUCCESS;
  }

  public String getUrlConstant() {
    return URL;
  }

  public String getErrorConstant() {
    return ERROR;
  }

  public String generatePictureName(String pictureName) {
    String name = System.currentTimeMillis() + "_" + pictureName;
    return name;
  }

  public String generatePictureServerAdress(String pictureName) {
    String url = RENTAL_PICTURES_DIRECTORY + pictureName;
    return url;
  }

  public String getPictureServerAddress(String pictureName) {
    String fullPictureName = generatePictureName(pictureName);
    return generatePictureServerAdress(fullPictureName);
  }

  private Boolean isImage(byte[] bytes) {
    try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes)) {
      BufferedImage image = ImageIO.read(bis);
      return image != null;
    } catch (Exception e) {
      return false;
    }
  }

  public Map<String, Object> savePictureInServerAndReturnServerAdressOrError(
    MultipartFile picture
  ) throws IOException { // déclarez que la méthode peut lancer une IOException
    Map<String, Object> response = new HashMap<>();

    try {
      if (picture == null || picture.isEmpty()) {
        response.put(SUCCESS, false);
        response.put(
          ERROR,
          ResponseEntity
            .badRequest()
            .body(rentalResponseService.getMissingPictureJsonString())
        );
        return response;
      }

      if (!isImage(picture.getBytes())) {
        response.put(SUCCESS, false);
        response.put(
          ERROR,
          ResponseEntity
            .badRequest()
            .body(rentalResponseService.getNotImageJsonString())
        );
        return response;
      }

      String pictureName = generatePictureName(picture.getOriginalFilename());

      Path path = Paths.get(uploadDir, pictureName);
      picture.transferTo(path);

      String imageUrl = generatePictureServerAdress(pictureName);

      response.put(SUCCESS, true);
      response.put(URL, imageUrl);
    } catch (IOException e) {
      response.put(SUCCESS, false);
      response.put(
        ERROR,
        ResponseEntity
          .internalServerError()
          .body(rentalResponseService.getErrorOccurJsonString())
      );
      throw e;
    }

    return response;
  }
}

package com.chatop.utils.Interface.CommonInterface;

import java.io.IOException;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

public interface PictureHandlerServiceInterface {
  String getSuccessConstant();
  String getUrlConstant();
  String getErrorConstant();
  String generatePictureName(String pictureName);
  String generatePictureServerAddress(String pictureName);
  String getPictureServerAddress(String pictureName);
  Map<String, Object> savePictureInServerAndReturnServerAddressOrError(
    MultipartFile picture
  ) throws IOException;
}

package com.chatop.utils.Common;

import com.chatop.utils.Interface.CommonInterface.UrlGeneratorServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class UrlGeneratorService implements UrlGeneratorServiceInterface {

  private static final String BASE_URL = "http://localhost:3001";

  @Override
  public String getFinalClientUrl(String adress) {
    String finalUrl = BASE_URL + adress;
    return finalUrl;
  }
}

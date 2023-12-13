package com.chatop.utils.Common;

import org.springframework.stereotype.Service;

@Service
public class UrlGeneratorService {

  private static final String BASE_URL = "http://localhost:3001";

  public String getFinalClientUrl(String adress) {
    String finalUrl = BASE_URL + adress;
    return finalUrl;
  }
}

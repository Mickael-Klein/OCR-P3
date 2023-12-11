package com.chatop.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.springframework.stereotype.Service;

@Service
public class DateConverterService {

  public String convertIsoToLocalDate(LocalDateTime isoDate) {
    try {
      String isoDateToString = isoDate.toString();
      LocalDateTime dateTime = LocalDateTime.parse(
        isoDateToString,
        DateTimeFormatter.ISO_LOCAL_DATE_TIME
      );
      DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
      return dateTime.format(newFormat);
    } catch (DateTimeParseException e) {
      throw e;
    }
  }
}

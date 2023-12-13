package com.chatop;

import com.chatop.configuration.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SwaggerConfig.class)
public class ChatopApplication {

  public static void main(String[] args) {
    SpringApplication.run(ChatopApplication.class, args);
  }
}

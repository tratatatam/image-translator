package com.urchinsys.imagetranslator;

import com.urchinsys.imagetranslator.dto.ImageDto;
import com.urchinsys.imagetranslator.service.OcrService;
import com.urchinsys.imagetranslator.service.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImageTranslatorApplication {

  public static void main(String[] args) {
    SpringApplication.run(ImageTranslatorApplication.class, args);
  }
}

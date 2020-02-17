package com.urchinsys.imagetranslator.service.impl;

import com.cloudmersive.client.ImageOcrApi;
import com.cloudmersive.client.invoker.ApiException;
import com.cloudmersive.client.model.ImageToTextResponse;
import com.urchinsys.imagetranslator.entity.Image;
import com.urchinsys.imagetranslator.entity.ImageText;
import com.urchinsys.imagetranslator.repository.ImageRepo;
import com.urchinsys.imagetranslator.service.OcrService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OcrServiceImpl implements OcrService {
  private final ImageRepo imageRepo;

  public OcrServiceImpl(ImageRepo imageRepo) {
    this.imageRepo = imageRepo;
  }

  @Override
  public Optional<ImageText> recognizeTextOnImage(Image image) throws IOException {
    ImageOcrApi imageOcrApi = new ImageOcrApi();
    byte[] bytes = Base64.decodeBase64(image.getBase64());
    Path tempFilePath = Files.createTempFile("BASE64", "");
    Files.write(tempFilePath, bytes);

    try {
      ImageToTextResponse imageToTextResponse = imageOcrApi
          .imageOcrPhotoToText(tempFilePath.toFile(), "Auto", "ENG");
      ImageText imageText = new ImageText();
      imageText.setText(imageToTextResponse.getTextResult());
      Image created = imageRepo.save(image);
      imageText.setImageId(created.getId());
      return Optional.of(imageText);
    } catch (ApiException e) {
      log.debug("ApiException Message: {}", e.getMessage());
    }
    return Optional.empty();
  }
}

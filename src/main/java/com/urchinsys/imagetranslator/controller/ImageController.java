package com.urchinsys.imagetranslator.controller;

import com.urchinsys.imagetranslator.dto.ImageDto;
import com.urchinsys.imagetranslator.dto.ImageTextDto;
import com.urchinsys.imagetranslator.service.OcrService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image/")
public class ImageController {
  private final OcrService ocrService;

  public ImageController(OcrService ocrService) {
    this.ocrService = ocrService;
  }

  @PostMapping(path = "toBase64")
  public ResponseEntity<String> convertImageToBase64(@RequestBody MultipartFile file)
      throws IOException {
    File tempFile = Files.createTempFile("BASE64", "").toFile();
    file.transferTo(tempFile);
    byte[] bytes = FileUtils.readFileToByteArray(tempFile);
    return ResponseEntity.ok(Base64.encodeBase64String(bytes));
  }

  @RequestMapping(path = "translate", method = RequestMethod.POST)
  public ResponseEntity<ImageTextDto> uploadImage(@RequestBody ImageDto imageDto)
      throws NotFoundException, IOException {
    Optional<ImageTextDto> imageTextDto = ocrService.recognizeTextOnImage(imageDto);
    return imageTextDto.map(ResponseEntity::ok).orElseThrow(NotFoundException::new);
  }
}

package com.urchinsys.imagetranslator.controller;

import com.urchinsys.imagetranslator.entity.Image;
import com.urchinsys.imagetranslator.entity.ImageText;
import com.urchinsys.imagetranslator.repository.ImageRepo;
import com.urchinsys.imagetranslator.service.OcrService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image/")
public class ImageController {
  private final OcrService ocrService;
  private final ImageRepo imageRepo;

  public ImageController(OcrService ocrService,
      ImageRepo imageRepo) {
    this.ocrService = ocrService;
    this.imageRepo = imageRepo;
  }

  @GetMapping(path = "{id}")
  public ResponseEntity<Image> uploadImage(@PathVariable String id) {
    Optional<Image> persisted = imageRepo.findById(id);
    return persisted.map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping(path = "toBase64")
  public ResponseEntity<String> convertImageToBase64(@RequestBody MultipartFile file)
      throws IOException {
    File tempFile = Files.createTempFile("BASE64_", "").toFile();
    file.transferTo(tempFile);
    byte[] bytes = FileUtils.readFileToByteArray(tempFile);
    return ResponseEntity.ok(Base64.encodeBase64String(bytes));
  }

  @PostMapping
  public ResponseEntity<ImageText> uploadImage(@RequestBody Image image)
      throws NotFoundException, IOException {
    Optional<ImageText> imageTextDto = ocrService.recognizeTextOnImage(image);
    return imageTextDto.map(ResponseEntity::ok).orElseThrow(NotFoundException::new);
  }
}

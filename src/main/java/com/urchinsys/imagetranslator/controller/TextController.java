package com.urchinsys.imagetranslator.controller;

import com.urchinsys.imagetranslator.entity.TextToTranslate;
import com.urchinsys.imagetranslator.entity.Translation;
import com.urchinsys.imagetranslator.service.TranslateService;
import java.util.Optional;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/text/")
public class TextController {
  private final TranslateService translateService;

  public TextController(TranslateService translateService) {
    this.translateService = translateService;
  }

  @RequestMapping(path = "translate", method = RequestMethod.POST)
  public ResponseEntity<Translation> uploadImage(@RequestBody TextToTranslate textToTranslate)
      throws NotFoundException {
    Optional<Translation> translation = translateService.translate(textToTranslate);
    return translation.map(ResponseEntity::ok).orElseThrow(NotFoundException::new);
  }
}

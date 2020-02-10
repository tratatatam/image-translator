package com.urchinsys.imagetranslator.controller;

import com.github.vbauer.yta.model.Translation;
import com.urchinsys.imagetranslator.dto.TextDto;
import com.urchinsys.imagetranslator.dto.TranslationDto;
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
  public ResponseEntity<TranslationDto> uploadImage(@RequestBody TextDto textDto)
      throws NotFoundException {
    Optional<TranslationDto> translation = translateService.translate(textDto);
    return translation.map(ResponseEntity::ok).orElseThrow(NotFoundException::new);
  }
}

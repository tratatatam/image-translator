package com.urchinsys.imagetranslator.controller;

import com.urchinsys.imagetranslator.dto.WordDefinitionDto;
import com.urchinsys.imagetranslator.service.DictionaryService;
import java.util.Optional;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/words/")
public class WordsController {
  private final DictionaryService dictionaryService;

  public WordsController(DictionaryService dictionaryService) {
    this.dictionaryService = dictionaryService;
  }

  @RequestMapping(path = "meaning", method = RequestMethod.POST)
  public ResponseEntity<WordDefinitionDto> uploadImage(@RequestBody String word)
      throws NotFoundException {
    Optional<WordDefinitionDto> translation = dictionaryService.meaning(word);
    return translation.map(ResponseEntity::ok).orElseThrow(NotFoundException::new);
  }
}

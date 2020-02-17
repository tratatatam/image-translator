package com.urchinsys.imagetranslator.controller;

import com.urchinsys.imagetranslator.entity.WordDefinition;
import com.urchinsys.imagetranslator.service.DictionaryService;
import java.util.Optional;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

  @RequestMapping(path = "meaning/{word}", method = RequestMethod.POST)
  public ResponseEntity<WordDefinition> uploadImage(@PathVariable String word)
      throws NotFoundException {
    Optional<WordDefinition> definitionDto = dictionaryService.meaning(word);
    return definitionDto.map(ResponseEntity::ok).orElseThrow(NotFoundException::new);
  }
}

package com.urchinsys.imagetranslator.service.impl;

import com.urchinsys.imagetranslator.dto.WordDefinitionDto;
import com.urchinsys.imagetranslator.service.DictionaryService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class DictionaryServiceImpl implements DictionaryService {

  @Override
  public Optional<WordDefinitionDto> meaning(String word) {
    return Optional.empty();
  }
}

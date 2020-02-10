package com.urchinsys.imagetranslator.service;

import com.urchinsys.imagetranslator.dto.WordDefinitionDto;
import java.util.Optional;

public interface DictionaryService {
 Optional<WordDefinitionDto> meaning(String word);
}

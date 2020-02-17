package com.urchinsys.imagetranslator.service;

import com.urchinsys.imagetranslator.entity.WordDefinition;
import java.util.Optional;

public interface DictionaryService {
 Optional<WordDefinition> meaning(String word);
}

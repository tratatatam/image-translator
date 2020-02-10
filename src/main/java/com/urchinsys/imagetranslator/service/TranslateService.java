package com.urchinsys.imagetranslator.service;

import com.github.vbauer.yta.model.Translation;
import com.urchinsys.imagetranslator.dto.TextDto;
import com.urchinsys.imagetranslator.dto.TranslationDto;
import java.util.Optional;

public interface TranslateService {

  Optional<TranslationDto> translate(TextDto input);
}

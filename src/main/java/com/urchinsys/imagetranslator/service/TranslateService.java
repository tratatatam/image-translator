package com.urchinsys.imagetranslator.service;

import com.urchinsys.imagetranslator.entity.TextToTranslate;
import com.urchinsys.imagetranslator.entity.Translation;
import java.util.Optional;

public interface TranslateService {

  Optional<Translation> translate(TextToTranslate input);
}

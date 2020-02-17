package com.urchinsys.imagetranslator.service;

import com.urchinsys.imagetranslator.entity.Text;
import com.urchinsys.imagetranslator.entity.Translation;
import java.util.Optional;

public interface TranslateService {

  Optional<Translation> translate(Text input);
}

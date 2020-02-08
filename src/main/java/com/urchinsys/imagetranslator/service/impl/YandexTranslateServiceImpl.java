package com.urchinsys.imagetranslator.service.impl;

import com.github.vbauer.yta.model.Language;
import com.github.vbauer.yta.model.Translation;
import com.github.vbauer.yta.service.YTranslateApiImpl;
import com.github.vbauer.yta.service.fraction.DetectionApi;
import com.github.vbauer.yta.service.fraction.TranslationApi;
import com.urchinsys.imagetranslator.service.TranslateService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class YandexTranslateServiceImpl implements TranslateService {

  public static final String YANDEX_TRANSLATE_API_KEY = "trnsl.1.1.20200208T123326Z.7d28fa988ad7907e.66b4da1dd61c6ba9f467609dcf66871e877452cb";

  @Override
  public String translate(String input) {
    YTranslateApiImpl api = new YTranslateApiImpl(YANDEX_TRANSLATE_API_KEY);
    DetectionApi detectionApi = api.detectionApi();

    Optional<Language> detectedLanguage = detectionApi.detect(input);
    Language language = detectedLanguage.orElseThrow(IllegalArgumentException::new);

    TranslationApi translationApi = api.translationApi();
    Translation translationResult = translationApi.translate(input, Language.RU);
    System.out.println(translationResult);

    return null;
  }
}

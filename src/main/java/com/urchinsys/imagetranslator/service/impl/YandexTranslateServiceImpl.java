package com.urchinsys.imagetranslator.service.impl;

import com.github.vbauer.yta.model.Language;
import com.github.vbauer.yta.service.YTranslateApiImpl;
import com.github.vbauer.yta.service.fraction.TranslationApi;
import com.urchinsys.imagetranslator.entity.Text;
import com.urchinsys.imagetranslator.entity.Translation;
import com.urchinsys.imagetranslator.service.TranslateService;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class YandexTranslateServiceImpl implements TranslateService {

  public static final String YANDEX_TRANSLATE_API_KEY = "trnsl.1.1.20200208T123326Z.7d28fa988ad7907e.66b4da1dd61c6ba9f467609dcf66871e877452cb";
  private final ModelMapper modelMapper;

  public YandexTranslateServiceImpl(
      ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @Override
  public Optional<Translation> translate(Text text) {
    String textToTranslate = text.getText();
    YTranslateApiImpl api = new YTranslateApiImpl(YANDEX_TRANSLATE_API_KEY);

    Language targetLanguage = Language.of(text.getTargetLanguage());
    TranslationApi translationApi = api.translationApi();
    com.github.vbauer.yta.model.Translation translation = translationApi.translate(textToTranslate, targetLanguage);
    Translation translationDto = new Translation(translation.text());

    return Optional.of(translationDto);
  }
}

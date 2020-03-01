package com.urchinsys.imagetranslator.service.impl;

import com.github.vbauer.yta.model.Language;
import com.github.vbauer.yta.service.YTranslateApiImpl;
import com.github.vbauer.yta.service.fraction.TranslationApi;
import com.urchinsys.imagetranslator.entity.TextToTranslate;
import com.urchinsys.imagetranslator.entity.Translation;
import com.urchinsys.imagetranslator.service.TranslateService;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Setter
@Getter
public class YandexTranslateServiceImpl implements TranslateService {

  @Value("${yandex_translate_api_key}")
  public String YANDEX_TRANSLATE_API_KEY;
  private final ModelMapper modelMapper;

  public YandexTranslateServiceImpl(
      ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @Override
  public Optional<Translation> translate(TextToTranslate text) {
    String textToTranslate = text.getText();
    YTranslateApiImpl api = new YTranslateApiImpl(YANDEX_TRANSLATE_API_KEY);

    Language targetLanguage = Language.of(text.getTargetLanguage());
    TranslationApi translationApi = api.translationApi();
    com.github.vbauer.yta.model.Translation translation = translationApi.translate(textToTranslate, targetLanguage);
    Translation translationDto = new Translation(translation.text());

    return Optional.of(translationDto);
  }
}

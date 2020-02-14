package com.urchinsys.imagetranslator.service.impl;

import com.squareup.okhttp.Response;
import com.urchinsys.imagetranslator.dto.WordDefinitionDto;
import com.urchinsys.imagetranslator.service.DictionaryService;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class WordsDictionaryServiceImpl implements DictionaryService {
  private final WordsApiHttpClient client;
  private final ModelMapper modelMapper;

  public WordsDictionaryServiceImpl(
      WordsApiHttpClient client, ModelMapper modelMapper) {
    this.client = client;
    this.modelMapper = modelMapper;
  }

  @Override
  public Optional<WordDefinitionDto> meaning(String word) {
    Response response = client.getResponse(word);
    WordDefinitionDto responseDto = modelMapper.map(response, WordDefinitionDto.class);
    return Optional.of(responseDto);
  }
}

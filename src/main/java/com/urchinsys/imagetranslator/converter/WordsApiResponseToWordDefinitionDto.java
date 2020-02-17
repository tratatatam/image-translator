package com.urchinsys.imagetranslator.converter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.squareup.okhttp.Response;
import com.urchinsys.imagetranslator.entity.WordApiResponse;
import com.urchinsys.imagetranslator.entity.WordDefinition;
import com.urchinsys.imagetranslator.exception.WordWasntFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class WordsApiResponseToWordDefinitionDto implements Converter<Response, WordDefinition> {

  @Override
  public WordDefinition convert(MappingContext<Response, WordDefinition> context) {
    Response src = context.getSource();
    WordDefinition definitionDto = new WordDefinition();

    try {
      String body = src.body().string();
      Gson gson = new Gson();
      JsonObject json = gson.fromJson(body, JsonObject.class);

      Optional<JsonElement> word = getJsonElementByKey(json, "word");
      word.ifPresent(e -> definitionDto.setWord(e.getAsString()));

      Optional<JsonElement> frequency = getJsonElementByKey(json, "frequency");
      frequency.ifPresent(f -> definitionDto.setFrequency(f.getAsFloat()));

      Optional<JsonArray> resultsResponse = Optional.ofNullable(json.getAsJsonArray("results"));
      JsonArray results = resultsResponse.orElseThrow(WordWasntFoundException::new);
      var wordsApi = new ArrayList<WordApiResponse>();
      results.forEach(r -> wordsApi.add(getWordApiResponse(r.getAsJsonObject())));
      definitionDto.setResponses(wordsApi);
    } catch (IOException e) {
      throw new IllegalArgumentException("Cannot parse JSON response");
    }
    return definitionDto;
  }

  private WordApiResponse getWordApiResponse(JsonObject each) {
    var wordApiResponseDto = new WordApiResponse();
    Optional<JsonElement> definition = getJsonElementByKey(each, "definition");
    definition.ifPresent(d -> wordApiResponseDto.setDefinition(d.getAsString()));

    Optional<JsonElement> partOfSpeech = getJsonElementByKey(each, "partOfSpeech");
    partOfSpeech.ifPresent(p -> wordApiResponseDto.setPartOfSpeech(p.getAsString()));

    Optional<JsonElement> synonyms = getJsonElementByKey(each, "synonyms");
    Optional<List<String>> synonymsValues = synonyms.map(s -> getValuesArray(s.getAsJsonArray()));
    synonymsValues.ifPresent(wordApiResponseDto::setSynonyms);

    Optional<JsonElement> antonyms = getJsonElementByKey(each, "antonyms");
    Optional<List<String>> antonymsValues = antonyms.map(s -> getValuesArray(s.getAsJsonArray()));
    antonymsValues.ifPresent(wordApiResponseDto::setAntonyms);

    Optional<JsonElement> examples = getJsonElementByKey(each, "examples");
    Optional<List<String>> examplesValues = examples.map(s -> getValuesArray(s.getAsJsonArray()));
    examplesValues.ifPresent(wordApiResponseDto::setExamples);

    return wordApiResponseDto;
  }

  private List<String> getValuesArray(JsonArray array) {
    var values = new ArrayList<String>();
    array.forEach(s -> values.add(s.getAsString()));
    return values;
  }

  private Optional<JsonElement> getJsonElementByKey(JsonObject json, String key) {
    return Optional.ofNullable(json.get(key));
  }
}

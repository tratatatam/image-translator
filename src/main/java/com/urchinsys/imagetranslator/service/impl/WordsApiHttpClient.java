package com.urchinsys.imagetranslator.service.impl;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Request.Builder;
import com.squareup.okhttp.Response;
import java.io.IOException;
import javax.validation.constraints.NotEmpty;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class WordsApiHttpClient {
  public static final String WORDS_API_URL = "https://wordsapiv1.p.mashape.com/words/";
  public static final String API_KEY_HEADER = "X-Mashape-Key";
  public static final String API_KEY_VALUE = "5ae52156c6msh1d71b787778b16fp1ac0d2jsn58da8c277738";

  public Response getResponse(@NonNull @NotEmpty String word) {
    OkHttpClient client = new OkHttpClient();

    Request request = new Builder()
        .url(WORDS_API_URL + word)
        .header(API_KEY_HEADER, API_KEY_VALUE)
        .build();

    try {
      return client.newCall(request).execute();
    } catch (IOException e) {
      throw new IllegalArgumentException("Cannot get the response!");
    }
  }
}

package com.urchinsys.imagetranslator.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@JsonInclude(Include.NON_EMPTY)
@Document(collection = "history")
public class History {

  @Id
  private String id;
  private String imageText;
  private String translation;
  private WordDefinition wordMeaning;
  private String url;
  private String imageId;
  @Indexed(unique = true)
  private String collection;
}

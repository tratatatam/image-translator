package com.urchinsys.imagetranslator.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@JsonInclude(Include.NON_EMPTY)
@Document(collection = "history_v2")
public class History {
  @Id
  private String id;
  private String imageId;
  private String imageText;
  private String imageTextTranslation;
  private String url;
  private List<WordDefinition> words;
}

package com.urchinsys.imagetranslator.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@JsonInclude(Include.NON_EMPTY)
@Document(collection = "images")
public class Image {
  private String id;
  private String base64;
}

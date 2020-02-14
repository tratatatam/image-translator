package com.urchinsys.imagetranslator.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordDefinitionDto {
  private String word;
  private List<WordApiResponseDto> responses;
  private String pronunciation;
  private Float frequency;
}

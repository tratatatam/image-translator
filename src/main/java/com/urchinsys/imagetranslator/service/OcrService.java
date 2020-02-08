package com.urchinsys.imagetranslator.service;

import com.urchinsys.imagetranslator.dto.ImageDto;
import com.urchinsys.imagetranslator.dto.ImageTextDto;
import java.io.IOException;
import java.util.Optional;

public interface OcrService {
  Optional<ImageTextDto> recognizeTextOnImage(ImageDto imageDto) throws IOException;
}

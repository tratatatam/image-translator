package com.urchinsys.imagetranslator.service;

import com.urchinsys.imagetranslator.entity.Image;
import com.urchinsys.imagetranslator.entity.ImageText;
import java.io.IOException;
import java.util.Optional;

public interface OcrService {
  Optional<ImageText> recognizeTextOnImage(Image image) throws IOException;
}

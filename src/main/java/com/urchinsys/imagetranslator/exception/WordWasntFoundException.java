package com.urchinsys.imagetranslator.exception;

public class WordWasntFoundException extends RuntimeException {

  public WordWasntFoundException() {
    super("The word hasn't been found!");
  }
}

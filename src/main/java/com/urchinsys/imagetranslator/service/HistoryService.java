package com.urchinsys.imagetranslator.service;

import com.urchinsys.imagetranslator.entity.History;
import com.urchinsys.imagetranslator.entity.WordDefinition;

import java.util.List;
import java.util.Optional;

public interface HistoryService {

  History create(History history);

  History update(String id, List<WordDefinition> words);

  void delete(String id);

  Optional<History> get(String id);

  List<History> getAll();
}

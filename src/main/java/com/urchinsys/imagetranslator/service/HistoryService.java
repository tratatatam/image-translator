package com.urchinsys.imagetranslator.service;

import com.urchinsys.imagetranslator.entity.History;
import java.util.List;
import java.util.Optional;

public interface HistoryService {

  History create(History history);

  History update(History history, String id);

  void delete(String id);

  Optional<History> get(String id);

  List<History> getAll();

  List<History> getByCollection(String collection);
}

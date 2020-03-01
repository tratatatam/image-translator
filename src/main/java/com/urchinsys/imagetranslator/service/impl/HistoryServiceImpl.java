package com.urchinsys.imagetranslator.service.impl;

import com.urchinsys.imagetranslator.entity.History;
import com.urchinsys.imagetranslator.entity.WordDefinition;
import com.urchinsys.imagetranslator.repository.HistoryRepo;
import com.urchinsys.imagetranslator.service.HistoryService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoryServiceImpl implements HistoryService {

  private final HistoryRepo historyRepo;

  public HistoryServiceImpl(HistoryRepo historyRepo) {
    this.historyRepo = historyRepo;
  }

  @Override
  public History create(@NonNull History history) {
    return historyRepo.save(history);
  }

  @Override
  public History update(String id, List<WordDefinition> words) {
    Optional<History> founded = historyRepo.findById(id);
    History persisted = founded.orElseThrow(IllegalArgumentException::new);
    persisted.getWords().addAll(words);
    return historyRepo.save(persisted);
  }

  @Override
  public void delete(@NonNull String id) {
    Optional<History> founded = historyRepo.findById(id);
    History persisted = founded.orElseThrow(IllegalArgumentException::new);
    historyRepo.delete(persisted);
  }

  @Override
  public Optional<History> get(String id) {
    return historyRepo.findById(id);
  }

  @Override
  public List<History> getAll() {
    return historyRepo.findAll();
  }
}

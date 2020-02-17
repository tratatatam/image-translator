package com.urchinsys.imagetranslator.service.impl;

import com.urchinsys.imagetranslator.entity.History;
import com.urchinsys.imagetranslator.repository.HistoryRepo;
import com.urchinsys.imagetranslator.service.HistoryService;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.NotEmpty;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl implements HistoryService {

  private final HistoryRepo historyRepo;
  private final ModelMapper modelMapper;

  public HistoryServiceImpl(HistoryRepo historyRepo, ModelMapper modelMapper) {
    this.historyRepo = historyRepo;
    this.modelMapper = modelMapper;
  }

  @Override
  public History create(@NonNull History history) {
    return historyRepo.save(history);
  }

  @Override
  public History update(@NonNull History history, @NotEmpty String id) {
    Optional<History> founded = historyRepo.findById(id);
    History persisted = founded.orElseThrow(IllegalArgumentException::new);
    modelMapper.map(history, persisted);
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

  @Override
  public List<History> getByCollection(String collection) {
    return historyRepo.findAllByCollection(collection);
  }
}

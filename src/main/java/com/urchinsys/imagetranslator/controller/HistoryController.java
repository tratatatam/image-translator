package com.urchinsys.imagetranslator.controller;

import com.urchinsys.imagetranslator.entity.History;
import com.urchinsys.imagetranslator.entity.WordDefinition;
import com.urchinsys.imagetranslator.service.HistoryService;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/history/")
public class HistoryController {
  private final HistoryService historyService;

  public HistoryController(HistoryService historyService) {
    this.historyService = historyService;
  }

  @GetMapping
  public ResponseEntity<List<History>> getAllHistory() {
    List<History> persisted = historyService.getAll();
    return new ResponseEntity<>(persisted, HttpStatus.OK);
  }

  @GetMapping(path = "{id}")
  public ResponseEntity<History> getHistoryById(@PathVariable String id) {
    Optional<History> persisted = historyService.get(id);
    return persisted.map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  public ResponseEntity<History> createHistory(@RequestBody History history) {
    History created = historyService.create(history);
    return ResponseEntity.ok(created);
  }

  @PutMapping(path = "{id}")
  public ResponseEntity<History> updateHistoryWords(@PathVariable String id, @RequestBody List<WordDefinition> words) {
    History updated = historyService.update(id, words);
    return ResponseEntity.ok(updated);
  }

  @DeleteMapping
  public HeadersBuilder<?> deleteHistory(@PathVariable String id) {
    historyService.delete(id);
    return ResponseEntity.noContent();
  }
}

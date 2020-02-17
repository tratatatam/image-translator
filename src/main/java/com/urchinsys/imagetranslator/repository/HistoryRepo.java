package com.urchinsys.imagetranslator.repository;

import com.urchinsys.imagetranslator.entity.History;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepo extends MongoRepository<History, String> {

  List<History> findAllByCollection(String collection);
}

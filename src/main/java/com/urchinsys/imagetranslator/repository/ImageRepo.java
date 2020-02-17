package com.urchinsys.imagetranslator.repository;

import com.urchinsys.imagetranslator.entity.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends MongoRepository<Image, String> {

}

package com.crio.starter.repository;

import com.crio.starter.data.MemesEntity;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

@Service
public interface MemesRepository extends MongoRepository<MemesEntity, String> {
  @Query("{ userName : ?0, memeUrl: ?1, caption: ?2}")
  Optional<MemesEntity> findMeme(String userName, String url, String caption);
}

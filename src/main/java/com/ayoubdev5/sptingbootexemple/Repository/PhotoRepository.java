package com.ayoubdev5.sptingbootexemple.Repository;

import com.ayoubdev5.sptingbootexemple.Entity.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends MongoRepository<Photo, String> {

}

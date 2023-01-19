package com.ayoubdev5.sptingbootexemple.Service;

import com.ayoubdev5.sptingbootexemple.Entity.Photo;
import com.ayoubdev5.sptingbootexemple.Repository.PhotoRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PhotoServiceImpl implements PhotoService{

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public String savePhoto(String originalFilename, MultipartFile image) throws IOException {
        Photo photo = new Photo();
        photo.setTitle(originalFilename);
        photo.setPhoto(new Binary(BsonBinarySubType.BINARY, image.getBytes()));

        return photoRepository.save(photo).getId();
    }

    @Override
    public Photo getPhoto(String id) {
        return photoRepository.findById(id).get();
    }
}

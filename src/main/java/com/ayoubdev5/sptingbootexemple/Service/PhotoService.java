package com.ayoubdev5.sptingbootexemple.Service;

import com.ayoubdev5.sptingbootexemple.Entity.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PhotoService {
    String savePhoto(String originalFilename, MultipartFile image) throws IOException;

    Photo getPhoto(String id);
}

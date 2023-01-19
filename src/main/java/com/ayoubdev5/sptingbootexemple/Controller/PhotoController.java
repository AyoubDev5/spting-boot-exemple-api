package com.ayoubdev5.sptingbootexemple.Controller;

import com.ayoubdev5.sptingbootexemple.Entity.Photo;
import com.ayoubdev5.sptingbootexemple.Service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/photo")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @PostMapping("/createPhoto")
    public String addPhoto(@RequestParam("image")MultipartFile image) throws IOException {
        String id = photoService.savePhoto(image.getOriginalFilename(),image);
        return id;
    }

    @GetMapping("/download-photo/{id}")
    public ResponseEntity<Resource> downloadPhoto(@PathVariable("id") String id){
        Photo photo = photoService.getPhoto(id);
        Resource resource = new ByteArrayResource(photo.getPhoto().getData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment", "filename=\""+photo.getTitle()+ "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

}

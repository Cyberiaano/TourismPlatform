package com.example.ProjetIntegre.services;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class ImageService {
    public ResponseEntity<byte[]> getImageFromFile(String filePath) {
        try {
            File file = new File(filePath);
            byte[] imageData = new byte[(int) file.length()];

            try (FileInputStream fis = new FileInputStream(file)) {
                fis.read(imageData);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Change MediaType based on your image type
            headers.setContentLength(imageData.length);

            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Or handle the exception according to your requirement
        }
    }
    public boolean deleteImage(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return file.delete();
        } else {
            return false;
        }
    }

}

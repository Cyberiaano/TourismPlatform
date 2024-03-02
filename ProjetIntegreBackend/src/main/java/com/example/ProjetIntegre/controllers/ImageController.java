package com.example.ProjetIntegre.controllers;

import com.example.ProjetIntegre.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping("/image/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) {
        String imagePath = HomeController.ImageFolderProfilesPath + imageName; // Replace with the actual path
        return imageService.getImageFromFile(imagePath);
    }

    @DeleteMapping("/image/{imageName}")
    public ResponseEntity<String> deleteImage(@PathVariable String imageName) {
        String imagePath = "path/to/your/local/folder/" + imageName; // Replace with the actual path
        boolean deleted = imageService.deleteImage(imagePath);
        if (deleted) {
            return new ResponseEntity<>("Image deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Image not found or could not be deleted", HttpStatus.NOT_FOUND);
        }
    }
}

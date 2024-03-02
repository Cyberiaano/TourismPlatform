package com.example.ProjetIntegre.controllers;

import com.example.ProjetIntegre.entities.Publication;
import com.example.ProjetIntegre.entities.Utilisateur;
import com.example.ProjetIntegre.services.PublicationService;
import com.example.ProjetIntegre.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/publications")
public class PublicationController {
    @Autowired
    private PublicationService publicationService;
    @Autowired
    private UserService userService;
    public static final String ImageFolderPath = "C:/Users/Riane Mounir/Desktop/TourismPlatform/ImagesApp/";
    @PostMapping("/new")
    public ResponseEntity<String> addPublication(@RequestParam Map<Object, Object> body,
                                                 @RequestParam(name = "files",required = false) List<MultipartFile> files) {
        try {
            String post = body.get("post").toString();
            Long id = Long.parseLong(body.get("utilisateur_id").toString());
            Utilisateur user = userService.getUserById(id); // Fetch Utilisateur object from your service

            List<String> imagePaths = new ArrayList<>();

            if (files != null && !files.isEmpty()) {
                for (MultipartFile file : files) {
                    String imagePath = this.ImageFolderPath + this.publicationService.saveImage(file);
                    imagePaths.add(imagePath);
                }
            }

            Publication publication = Publication.builder()
                    .post(post)
                    .dateAjout(LocalDate.now())
                    .imagePaths(imagePaths)
                    .utilisateur(user)
                    .build();

            this.publicationService.addPublication(publication);

            return ResponseEntity.ok("Publication Ajoutée Avec Succès");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error.");
        }
    }


    @GetMapping("/get/{id}")
    public Publication getPublicationById(@PathVariable Long id){
        return this.publicationService.getPublicationById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deletePublicationById(@PathVariable Long id){
         this.publicationService.deletePublicationById(id);
    }
    @PutMapping("/update/{id}")
    public Publication updatePublicationById(@PathVariable Long id){
        return null;
    }
    @GetMapping("/all")
    public List<Publication> getPublications(){
        return this.publicationService.getAllPublications();
    }
}

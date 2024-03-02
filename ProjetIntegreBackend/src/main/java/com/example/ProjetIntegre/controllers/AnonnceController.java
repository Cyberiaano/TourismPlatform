package com.example.ProjetIntegre.controllers;

import com.example.ProjetIntegre.entities.Anonnce;
import com.example.ProjetIntegre.entities.Organisation;
import com.example.ProjetIntegre.entities.Publication;
import com.example.ProjetIntegre.services.AnonnceService;
import com.example.ProjetIntegre.services.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/anonnces")
public class AnonnceController {
    @Autowired
    private AnonnceService anonnceService;
    @Autowired
    private OrganisationService organisationService;
    public static final String ImageFolderPath = ""C:/Users/Riane Mounir/Desktop/TourismPlatform/ImagesApp/";
    @PostMapping("/new")
    public ResponseEntity<String> addPublication(@RequestParam Map<Object, Object> body,
                                                 @RequestParam("files") List<MultipartFile> files) {
        try {
            String titre = (body.get("titre").toString());
            int note = Integer.parseInt(body.get("note").toString());
            String description = body.get("description").toString();
            double longitude = Double.parseDouble(body.get("longitude").toString());
            double latitude = Double.parseDouble(body.get("latitude").toString());
            String address = body.get("address").toString();
            String tele = body.get("tele").toString();
            String facebook = body.get("facebook").toString();
            String instagram = body.get("instagram").toString();
            Organisation organisation = this.organisationService.getOrganisationById(Long.parseLong(body.get("organisation_id").toString()));

            List<String> imagePaths = new ArrayList<>();

            if (files != null && !files.isEmpty()) {
                for (MultipartFile file : files) {
                    String imagePath = this.ImageFolderPath + this.anonnceService.saveImage(file);
                    imagePaths.add(imagePath);
                }
            }

            Anonnce anonnce = Anonnce.builder()
                    .titre(titre)
                    .adress(address)
                    .facebook(facebook)
                    .description(description)
                    .latitude(latitude)
                    .longitude(longitude)
                    .note(note)
                    .dateAjout(LocalDate.now())
                    .tele(tele)
                    .imagePaths(imagePaths)
                    .organisation(organisation)
                    .build();

            this.anonnceService.addAnonnce(anonnce);

            return ResponseEntity.ok("Anonnce Ajoutée Avec Succès");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error.");
        }
    }

    @GetMapping("/get/{id}")
    public Anonnce getAnonnceById(@PathVariable Long id){
        return this.anonnceService.getAnonnceById(id);
    }
    @DeleteMapping("/delete/{id}")
    public Anonnce deleteAnonnceById(@PathVariable Long id){
        return this.anonnceService.deleteAnonnceById(id);
    }
    @PutMapping("/update/{id}")
    public Publication updatePublicationById(@PathVariable Long id){
        return null;
    }
}

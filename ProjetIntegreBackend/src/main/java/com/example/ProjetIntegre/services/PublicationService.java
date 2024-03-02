package com.example.ProjetIntegre.services;

import com.example.ProjetIntegre.controllers.PublicationController;
import com.example.ProjetIntegre.entities.*;
import com.example.ProjetIntegre.repositories.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PublicationService {
    @Autowired
    private PublicationRepository publicationRepository;
    @Autowired
    private OrganisationService organisationService;
    @Autowired
    private TouristeService touristeService;
    @Autowired
    private UserService userService;

    public List<Publication> getAllPublications() {
        List<Publication> publications = this.publicationRepository.findAll();
        List<Publication> pub = new ArrayList<>();
        for(Publication p :publications ){
            if(p.getUtilisateur().getIsPro()){
                pub.add(p);
            }
        }
        return pub;
    }

    public List<Publication> getAllPublicationByTheme(String theme) {
        List<Publication> publications = getAllPublications();
        List<Publication> pub = new ArrayList<>();
        for (Publication p : publications) {
            if (this.organisationService.getOrganisationById(p.getUtilisateur().getId()).getCategory().equals(Category.valueOf(theme))) {
                pub.add(p);
            }
        }
        return pub;
    }
    public List<Publication> getAllFeeds() {
        return this.publicationRepository.findAll();
    }


    public String saveImage(MultipartFile file){
        try {
            String type = file.getContentType().substring(6);
            String newName = generateUniqueFileName() + "." + type;

            File convertFile = new File(PublicationController.ImageFolderPath+newName);
            convertFile.createNewFile();

            try (FileOutputStream fout = new FileOutputStream(convertFile)) {
                fout.write(file.getBytes());
                return newName;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
                return "default";
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }
    private String generateUniqueFileName() {
        // Implement a method to generate a unique filename (e.g., using UUID or timestamp)
        // Example using UUID:
        return UUID.randomUUID().toString();
    }
    public Utilisateur getUser(Long id){
        return this.userService.getUserById(id);
    }
    public void addPublication(Publication publication){
        //this.userService.getUserById(publication.getUtilisateur().getId()).addPublication(publication);
        this.publicationRepository.save(publication);
    }
    public void suppPublication(Long id){
        this.publicationRepository.deleteById(id);
    }

    public Publication getPublicationById(Long id){
        return this.publicationRepository.findPublicationById(id);
    }

    public void deletePublicationById(Long id) {
        Publication publication = this.publicationRepository.findPublicationById(id);
         this.publicationRepository.delete(publication);
    }

}


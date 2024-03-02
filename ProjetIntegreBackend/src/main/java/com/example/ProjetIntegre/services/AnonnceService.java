package com.example.ProjetIntegre.services;

import com.example.ProjetIntegre.controllers.AnonnceController;
import com.example.ProjetIntegre.entities.Anonnce;
import com.example.ProjetIntegre.entities.Category;
import com.example.ProjetIntegre.entities.Publication;
import com.example.ProjetIntegre.entities.Utilisateur;
import com.example.ProjetIntegre.repositories.AnonnceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AnonnceService {
    @Autowired
    private AnonnceRepository anonnceRepository;
    @Autowired
    private OrganisationService organisationService;
    @Autowired
    private UserService userService;

    public List<Anonnce> getAllAnonnces() {
            return this.anonnceRepository.findAll();
    }
    public List<Anonnce> getAllPublicationByTheme(String theme) {
        List<Anonnce> anonnces = getAllAnonnces();
        List<Anonnce> pub = new ArrayList<>();
        for (Anonnce p : anonnces) {
            if (this.organisationService.getOrganisationById(p.getOrganisation().getId()).getCategory().equals(Category.valueOf(theme))) {
                pub.add(p);
            }
        }
        return pub;
    }

    public String saveImage(MultipartFile file){
        try {
            String type = file.getContentType().substring(6);
            String newName = generateUniqueFileName() + "." + type;

            File convertFile = new File(AnonnceController.ImageFolderPath+newName);
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
    public void addAnonnce(Anonnce anonnce){
        this.organisationService.getOrganisationById(anonnce.getOrganisation().getId()).addAnonnce(anonnce);
        this.anonnceRepository.save(anonnce);
    }

    public Anonnce getAnonnceById(Long id) {
        return this.anonnceRepository.findAnonnceById(id);
    }

    public Anonnce deleteAnonnceById(Long id) {
        return this.anonnceRepository.deleteAnonnceById(id);
    }

    public List<Anonnce> getAllAnonnceByTheme(String theme) {
            List<Anonnce> anonnces = getAllAnonnces();
            List<Anonnce> pub = new ArrayList<>();
            for (Anonnce p : anonnces) {
                if (this.organisationService.getOrganisationById(p.getOrganisation().getId()).getCategory().equals(Category.valueOf(theme))) {
                    pub.add(p);
                }
            }
            return pub;
        }
}

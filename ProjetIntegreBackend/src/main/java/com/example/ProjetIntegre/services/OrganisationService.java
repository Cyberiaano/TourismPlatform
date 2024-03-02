package com.example.ProjetIntegre.services;

import com.example.ProjetIntegre.controllers.HomeController;
import com.example.ProjetIntegre.entities.Organisation;
import com.example.ProjetIntegre.entities.Utilisateur;
import com.example.ProjetIntegre.repositories.OrganisationRepository;
import com.example.ProjetIntegre.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;

@Service
public class OrganisationService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private OrganisationRepository organisationRepository;
    public boolean verifyUser(Utilisateur user) {
        List<Utilisateur> users = this.utilisateurRepository.findAll();
        for (Utilisateur u : users) {
            if (user.getEmail().equals(u.getEmail())) {
                return true;
            }
        }
        return false;
    }
    public String add(Organisation organisation, MultipartFile profilePhoto) {
        if(verifyUser(organisation)){
            return "email used";
        }
        else {
            organisation.setPathProfil(HomeController.ImageFolderProfilesPath+saveImage(profilePhoto));
            this.organisationRepository.save(organisation);
            return "success";
        }
    }

    public String saveImage(MultipartFile file){
        try {
            String type = file.getContentType().substring(6);
            String newName = generateUniqueFileName() + "." + type;

            File convertFile = new File(HomeController.ImageFolderProfilesPath+newName);
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

    public Organisation getOrganisationById(Long id) {
        return this.organisationRepository.findOrganisationById(id);
    }

    public List<Organisation> getALL() {
        return this.organisationRepository.findAll();
    }

    public void addOrg(Organisation organisation2) {
        this.organisationRepository.save(organisation2);
    }
}

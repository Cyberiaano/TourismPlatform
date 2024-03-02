package com.example.ProjetIntegre.services;

import com.example.ProjetIntegre.controllers.UserController;
import com.example.ProjetIntegre.entities.Utilisateur;
import com.example.ProjetIntegre.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur addUser(Utilisateur utilisateur) {
        return this.utilisateurRepository.save(utilisateur);
    }

    public Utilisateur getUserById(Long id) {
            return this.utilisateurRepository.findUtilisateurById(id);
    }


    public void deleteUserById(Long id) {
        this.utilisateurRepository.delete(this.utilisateurRepository.findUtilisateurById(id));
    }

    public Utilisateur updateUserById(Long id) {
        Utilisateur utilisateur = this.utilisateurRepository.findUtilisateurById(id);
        return this.utilisateurRepository.save(utilisateur);
    }

    public List<Utilisateur> getUsers() {
        return this.utilisateurRepository.findAll();
    }

    public Utilisateur getUser(String email) {
        return this.utilisateurRepository.findByEmail(email);
    }

    public boolean verify(String username, String password) {
        for(Utilisateur utilisateur : this.utilisateurRepository.findAll()){
            if(utilisateur.getEmail().equals(username) && utilisateur.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

}

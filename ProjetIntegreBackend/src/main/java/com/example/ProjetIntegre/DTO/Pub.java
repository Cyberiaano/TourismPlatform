package com.example.ProjetIntegre.DTO;

import com.example.ProjetIntegre.entities.Publication;
import com.example.ProjetIntegre.entities.Utilisateur;

public class Pub {
    private Utilisateur utilisateur;
    private Publication publication;

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public Pub(Utilisateur utilisateur, Publication publication) {
        this.utilisateur = utilisateur;
        this.publication = publication;
    }
}

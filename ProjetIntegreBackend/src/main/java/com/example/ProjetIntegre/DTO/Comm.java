package com.example.ProjetIntegre.DTO;

import com.example.ProjetIntegre.entities.Commentaire;
import com.example.ProjetIntegre.entities.Utilisateur;
import jdk.jshell.execution.Util;

public class Comm {
    private Utilisateur utilisateur;
    private Commentaire commentaire;

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Commentaire getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(Commentaire commentaire) {
        this.commentaire = commentaire;
    }

    public Comm(Utilisateur utilisateur, Commentaire commentaire) {
        this.utilisateur = utilisateur;
        this.commentaire = commentaire;
    }
}

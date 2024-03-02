package com.example.ProjetIntegre.services;

import com.example.ProjetIntegre.DTO.Comm;
import com.example.ProjetIntegre.entities.Commentaire;
import com.example.ProjetIntegre.entities.Publication;
import com.example.ProjetIntegre.entities.Utilisateur;
import com.example.ProjetIntegre.repositories.CommentaireRepository;
import com.example.ProjetIntegre.repositories.PublicationRepository;
import com.example.ProjetIntegre.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentaireService {
    @Autowired
    private CommentaireRepository commentaireRepository;
    @Autowired
    private PublicationRepository publicationRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Commentaire addComment(Commentaire commentaire) {
        Publication publication = this.publicationRepository.findPublicationById(commentaire.getPublication().getId());
        Utilisateur utilisateur = this.utilisateurRepository.findUtilisateurById(commentaire.getUtilisateur().getId());
        publication.addCommentaire(commentaire);
        commentaire.setPublication(publication);
        commentaire.setUtilisateur(utilisateur);
        this.publicationRepository.save(publication);
        return this.commentaireRepository.save(commentaire);
    }

    public void deleteCommentById(Long id) {
        this.commentaireRepository.delete(this.commentaireRepository.findCommentaireById(id));
    }

    public Commentaire getCommentById(Long id) {
        return this.commentaireRepository.findCommentaireById(id);
    }

    public List<Comm> getCommentsByPubId(Long pubId) {
        List<Commentaire> commentaires = this.commentaireRepository.findAll();
        List<Comm> comments = new ArrayList<>();
        for (Commentaire commentaire : commentaires) {

            if (commentaire.getPublication().getId() == pubId) {
                comments.add(new Comm(commentaire.getUtilisateur(),commentaire));
            }
        }
        return comments;
    }

    public List<Commentaire> getAll() {
        return this.commentaireRepository.findAll();
    }
}

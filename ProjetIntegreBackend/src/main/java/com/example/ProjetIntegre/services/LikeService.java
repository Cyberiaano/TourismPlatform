package com.example.ProjetIntegre.services;


import com.example.ProjetIntegre.entities.Likee;
import com.example.ProjetIntegre.entities.Publication;
import com.example.ProjetIntegre.entities.Utilisateur;
import com.example.ProjetIntegre.repositories.LikeRepository;
import com.example.ProjetIntegre.repositories.PublicationRepository;
import com.example.ProjetIntegre.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private PublicationRepository publicationRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public void addPubToLike(Long userId, Long publicationId) {
        Utilisateur utilisateur = utilisateurRepository.findUtilisateurById(userId);
        Publication publication = publicationRepository.findPublicationById(publicationId);
        if(verify(userId,publicationId)){
            for(Likee f : this.likeRepository.findAll())
            {
                if(f.getPublication().getId() == publication.getId()){
                    this.likeRepository.delete(f);
                }
            }
        }
        else {
            Likee favoritePublication = new Likee();
            favoritePublication.setUtilisateur(utilisateur);
            favoritePublication.setPublication(publication);

            utilisateur.getLikes().add(favoritePublication);
            this.likeRepository.save(favoritePublication);
            utilisateurRepository.save(utilisateur);
        }
    }
    public boolean verify(Long utilisateurId, Long publicationId){
        Utilisateur utilisateur = utilisateurRepository.findUtilisateurById(utilisateurId);
        Publication publication = publicationRepository.findPublicationById(publicationId);
        for(Likee f : this.likeRepository.findAll())
        {
            if(f.getPublication().getId() == publication.getId()){
                return true;
            }
        }
        return false;
    }
}

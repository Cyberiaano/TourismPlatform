package com.example.ProjetIntegre.services;

import com.example.ProjetIntegre.DTO.Ann;
import com.example.ProjetIntegre.DTO.Pub;
import com.example.ProjetIntegre.entities.*;
import com.example.ProjetIntegre.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PublicationRepository publicationRepository;
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private AnonnceFavorisRepository anonnceFavorisRepository;
    @Autowired
    private OrganisationRepository organisationRepository;
    @Autowired
    private AnonnceRepository anonnceRepository;

    public void addPublicationToFavorites(Long utilisateurId, Long publicationId) {
        Utilisateur utilisateur = utilisateurRepository.findUtilisateurById(utilisateurId);
        Publication publication = publicationRepository.findPublicationById(publicationId);
        if(verify(utilisateurId,publicationId)){
            for(FavoritePublication f : this.favoriteRepository.findAll())
            {
                if(f.getPublication().getId() == publication.getId()){
                    this.favoriteRepository.delete(f);
                }
            }
        }
        else {
            FavoritePublication favoritePublication = new FavoritePublication();
            favoritePublication.setUtilisateur(utilisateur);
            favoritePublication.setPublication(publication);

            utilisateur.getFavoritePublications().add(favoritePublication);
            this.favoriteRepository.save(favoritePublication);
            utilisateurRepository.save(utilisateur);
        }
    }

    public boolean verify(Long utilisateurId, Long publicationId){
        Utilisateur utilisateur = utilisateurRepository.findUtilisateurById(utilisateurId);
        Publication publication = publicationRepository.findPublicationById(publicationId);
        for(FavoritePublication f : this.favoriteRepository.findAll())
        {
            if(f.getPublication().getId() == publication.getId()){
                return true;
            }
        }
        return false;
    }
    public boolean verifyy(Long utilisateurId, Long publicationId){
        Organisation utilisateur = organisationRepository.findOrganisationById(utilisateurId);
        Publication publication = publicationRepository.findPublicationById(publicationId);
        for(FavoriteAnonnce f : this.anonnceFavorisRepository.findAll())
        {
            if(f.getAnonnce().getId() == publication.getId()){
                return true;
            }
        }
        return false;
    }

    public void removePublicationFromFavorites(Long utilisateurId, Long publicationId) {
        Utilisateur utilisateur = utilisateurRepository.findUtilisateurById(utilisateurId);

        Publication publication = publicationRepository.findPublicationById(publicationId);

        utilisateur.getFavoritePublications()
                .removeIf(fp -> fp.getPublication().getId().equals(publicationId));
        for(FavoritePublication favoritePublication : utilisateur.getFavoritePublications()){
            favoriteRepository.delete(favoritePublication);
        }
        utilisateurRepository.save(utilisateur);
    }

    public List<Long> findPublicationFavoris(String email) {
        List<Long> ids = new ArrayList<>();
        List<FavoritePublication> publications = this.favoriteRepository.findAll();
        for(FavoritePublication publication : publications){
            if(publication.getUtilisateur().getEmail().equals(email)){
                ids.add(publication.getPublication().getId());
            }
        }
        return ids;
    }

    public List<Pub> getFavoris(Long id) {
        List<FavoritePublication> favoris = this.favoriteRepository.findAll();
        List<Pub> pubs = new ArrayList<>();
        for(FavoritePublication favoritePublication : favoris){
            if(favoritePublication.getUtilisateur().getId() == id){
                pubs.add(new Pub(favoritePublication.getUtilisateur(),favoritePublication.getPublication()));
            }
        }
        return pubs;
    }

    public List<Ann> getCataloge(Long id) {
        List<FavoriteAnonnce> favoris = this.anonnceFavorisRepository.findAll();
        List<Ann> pubs = new ArrayList<>();
        for(FavoriteAnonnce favoritePublication : favoris){
            if(favoritePublication.getOrganisation().getId() == id){
                pubs.add(new Ann(favoritePublication.getOrganisation(),favoritePublication.getAnonnce()));
            }
        }
        return pubs;
    }

    public void addAnonnceToFavoris(Long utilisateurId, Long publicationId) {
        Organisation utilisateur = organisationRepository.findOrganisationById(utilisateurId);

        Anonnce publication = anonnceRepository.findAnonnceById(publicationId);
        if(verifyy(utilisateurId,publicationId)){
            for(FavoriteAnonnce f : this.anonnceFavorisRepository.findAll())
            {
                if(f.getAnonnce().getId() == publication.getId()){
                    this.anonnceFavorisRepository.delete(f);
                }
            }
        }
        else {
            FavoriteAnonnce favoritePublication = new FavoriteAnonnce();
            favoritePublication.setOrganisation(utilisateur);
            favoritePublication.setAnonnce(publication);

            utilisateur.getFavoriteAnonnces().add(favoritePublication);
            this.anonnceFavorisRepository.save(favoritePublication);
            organisationRepository.save(utilisateur);
        }

    }

    public void removeAnonnceFromFavoris(Long utilisateurId, Long publicationId) {
        Organisation utilisateur = organisationRepository.findOrganisationById(utilisateurId);

        Anonnce publication = anonnceRepository.findAnonnceById(publicationId);

        utilisateur.getFavoriteAnonnces()
                .removeIf(fp -> fp.getAnonnce().getId().equals(publicationId));
        for(FavoriteAnonnce favoritePublication : utilisateur.getFavoriteAnonnces()){
            anonnceFavorisRepository.delete(favoritePublication);
        }
        organisationRepository.save(utilisateur);
    }
}
package com.example.ProjetIntegre.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String username;
    private String password;
    private String email;
    private String mobile;
    private Boolean isPro =  Boolean.FALSE;
    private String pathProfil;
    private String adress;

    @OneToMany(mappedBy = "utilisateur", orphanRemoval = true)
    private List<Publication> publications = new ArrayList<>();

    @OneToMany(mappedBy = "utilisateur")
    private List<FavoritePublication> favoritePublications = new ArrayList<>();

    @OneToMany(mappedBy = "utilisateur", orphanRemoval = true)
    private List<Commentaire> commentaires = new ArrayList<>();

    @OneToMany(mappedBy = "utilisateur")
    private List<Likee> likes = new ArrayList<>();

    public void addPublication(Publication publication){
        this.publications.add(publication);
    }
}

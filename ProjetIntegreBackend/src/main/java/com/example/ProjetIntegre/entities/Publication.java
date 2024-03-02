package com.example.ProjetIntegre.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    @JsonIgnore
    private Utilisateur utilisateur;

    private LocalDate dateAjout;

    private String post;
    @ElementCollection
    @Column(name = "imagePaths",nullable = true)
    private List<String> imagePaths;


    @OneToMany(mappedBy = "publication")
    private List<FavoritePublication> favoritePublications = new ArrayList<>();

    @OneToMany(mappedBy = "publication")
    private List<Commentaire> commentaires = new ArrayList<>();
    public void addCommentaire(Commentaire commentaire){
        this.commentaires.add(commentaire);
    }
    @OneToMany(mappedBy = "publication")
    private List<Likee> likes = new ArrayList<>();
}

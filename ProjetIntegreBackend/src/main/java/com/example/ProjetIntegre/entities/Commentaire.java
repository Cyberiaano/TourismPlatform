package com.example.ProjetIntegre.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateAjout;

    @ManyToOne
    @JoinColumn(name = "publication_id")
    @JsonIgnore
    private Publication publication;
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    @JsonIgnore
    private Utilisateur utilisateur;
    private String post;


}

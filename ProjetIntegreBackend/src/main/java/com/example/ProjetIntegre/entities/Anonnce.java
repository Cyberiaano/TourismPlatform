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
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Anonnce {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titre;
    @ElementCollection
    private List<String> imagePaths;
    private int note;
    private String description;
    private double longitude;
    private double latitude;
    private String adress;
    private String tele;
    private String facebook;
    private String instagram;
    private LocalDate dateAjout = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "organisation_id")
    @JsonIgnore
    private Organisation organisation;

    @OneToMany(mappedBy = "anonnce")
    private List<FavoriteAnonnce> favoriteAnonnces = new ArrayList<>();

}

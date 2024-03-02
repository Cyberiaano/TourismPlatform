package com.example.ProjetIntegre.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class FavoriteAnonnce {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "organisation_id")
    @JsonIgnore
    private Organisation organisation;

    @ManyToOne
    @JoinColumn(name = "anonnce_id")
    @JsonIgnore
    private Anonnce anonnce;
}

package com.example.ProjetIntegre.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Organisation extends Utilisateur{
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private Category category;
    private String nom;
    @OneToMany(mappedBy = "organisation", orphanRemoval = true)
    private List<Anonnce> anonnces = new ArrayList<>();


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Anonnce> getAnonnces() {
        return anonnces;
    }

    public void setAnonnces(List<Anonnce> anonnces) {
        this.anonnces = anonnces;
    }
    public void addAnonnce(Anonnce anonnce){
        this.anonnces.add(anonnce);
    }
    @OneToMany(mappedBy = "organisation")
    private List<FavoriteAnonnce> favoriteAnonnces = new ArrayList<>();

    public List<FavoriteAnonnce> getFavoriteAnonnces() {
        return favoriteAnonnces;
    }

    public void setFavoriteAnonnces(List<FavoriteAnonnce> favoriteAnonnces) {
        this.favoriteAnonnces = favoriteAnonnces;
    }
}

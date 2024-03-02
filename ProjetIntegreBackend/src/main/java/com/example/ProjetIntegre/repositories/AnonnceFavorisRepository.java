package com.example.ProjetIntegre.repositories;

import com.example.ProjetIntegre.entities.FavoriteAnonnce;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnonnceFavorisRepository extends JpaRepository<FavoriteAnonnce,Long> {

}

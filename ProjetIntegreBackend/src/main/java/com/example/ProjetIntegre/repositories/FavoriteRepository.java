package com.example.ProjetIntegre.repositories;

import com.example.ProjetIntegre.entities.FavoritePublication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<FavoritePublication,Long> {
}

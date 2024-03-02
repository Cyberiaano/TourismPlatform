package com.example.ProjetIntegre.repositories;

import com.example.ProjetIntegre.entities.Touriste;
import com.example.ProjetIntegre.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TouristeRepository extends JpaRepository<Touriste, Long> {
    Utilisateur findTouristeById(Long id);
}
package com.example.ProjetIntegre.repositories;

import com.example.ProjetIntegre.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findUtilisateurById(Long id);

    Utilisateur findByEmail(String email);
}
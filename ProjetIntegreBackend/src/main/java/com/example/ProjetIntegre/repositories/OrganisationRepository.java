package com.example.ProjetIntegre.repositories;

import com.example.ProjetIntegre.entities.Organisation;
import com.example.ProjetIntegre.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface OrganisationRepository extends JpaRepository<Organisation, Long> {
    Organisation findOrganisationById(Long id);
}
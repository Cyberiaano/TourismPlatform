package com.example.ProjetIntegre.repositories;

import com.example.ProjetIntegre.entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PublicationRepository extends JpaRepository<Publication, Long> {
    Publication deletePublicationById(Long id);

    Publication findPublicationById(Long id);
}
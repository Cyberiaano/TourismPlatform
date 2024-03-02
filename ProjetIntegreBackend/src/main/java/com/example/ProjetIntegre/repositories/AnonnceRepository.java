package com.example.ProjetIntegre.repositories;

import com.example.ProjetIntegre.entities.Anonnce;
import com.example.ProjetIntegre.entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnonnceRepository extends JpaRepository<Anonnce, Long> {
    Anonnce findAnonnceById(Long id);

    Anonnce deleteAnonnceById(Long id);
}

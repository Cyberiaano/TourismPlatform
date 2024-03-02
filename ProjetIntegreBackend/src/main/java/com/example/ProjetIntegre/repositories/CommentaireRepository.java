package com.example.ProjetIntegre.repositories;

import com.example.ProjetIntegre.entities.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {
    Commentaire findCommentaireById(Long id);
}
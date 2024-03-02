package com.example.ProjetIntegre.repositories;

import com.example.ProjetIntegre.entities.Likee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Likee,Long> {

}

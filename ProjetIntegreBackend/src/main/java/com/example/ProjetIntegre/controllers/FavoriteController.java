package com.example.ProjetIntegre.controllers;

import com.example.ProjetIntegre.DTO.Ann;
import com.example.ProjetIntegre.DTO.Pub;
import com.example.ProjetIntegre.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
    @CrossOrigin(origins = "*")
    @RequestMapping("/favorites")
    public class FavoriteController {
        @Autowired
        private FavoriteService favoriteService;

        @PutMapping("/publications/{userId}/add/{publicationId}")
        public ResponseEntity<String> addPublicationToFavorites(@PathVariable Long userId,
                                                                @PathVariable Long publicationId) {
            favoriteService.addPublicationToFavorites(userId, publicationId);
            return ResponseEntity.ok("Publication added to favorites successfully");
        }

        @PutMapping("publications/{userId}/remove/{publicationId}")
        public ResponseEntity<String> removePublicationFromFavorites(@PathVariable Long userId,
                                                                     @PathVariable Long publicationId) {
            favoriteService.removePublicationFromFavorites(userId, publicationId);
            return ResponseEntity.ok("Publication removed from favorites successfully");
        }
        @GetMapping ("/post/{id}")
        public List<Pub> publicationFav(@PathVariable Long id){
            return this.favoriteService.getFavoris(id);
        }
        @PutMapping("/anonnces/{userId}/add/{publicationId}")
        public ResponseEntity<String> addAnonnceToFavoris(@PathVariable Long userId,
                                                                @PathVariable Long publicationId) {
            favoriteService.addAnonnceToFavoris(userId, publicationId);
            return ResponseEntity.ok("Publication added to favorites successfully");
        }

        @PutMapping("/anonnces/{userId}/remove/{publicationId}")
        public ResponseEntity<String> removeAnonnceFromFavoris(@PathVariable Long userId,
                                                                     @PathVariable Long publicationId) {
            favoriteService.removeAnonnceFromFavoris(userId, publicationId);
            return ResponseEntity.ok("Anonnce removed from favorites successfully");
        }
        @GetMapping ("/cataloge/{id}")
        public List<Ann> AnonnceFav(@PathVariable Long id){
            return this.favoriteService.getCataloge(id);
        }
}


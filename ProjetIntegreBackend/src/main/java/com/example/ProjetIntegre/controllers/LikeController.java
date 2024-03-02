package com.example.ProjetIntegre.controllers;

import com.example.ProjetIntegre.services.LikeService;
import jdk.jfr.Registered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeController {
    @Autowired
    private LikeService likeService;
    @PutMapping("/publications/{userId}/add/{publicationId}")
    public ResponseEntity<String> addPublicationToLike(@PathVariable Long userId,
                                                            @PathVariable Long publicationId) {
        likeService.addPubToLike(userId, publicationId);
        return ResponseEntity.ok("Publication added to favorites successfully");
    }
}


package com.example.ProjetIntegre.controllers;

import com.example.ProjetIntegre.DTO.Comm;
import com.example.ProjetIntegre.entities.Commentaire;
import com.example.ProjetIntegre.services.CommentaireService;
import com.example.ProjetIntegre.services.PublicationService;
import com.example.ProjetIntegre.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/commentaires")
public class CommentaireController {
    @Autowired
    private CommentaireService commentaireService;
    @Autowired
    private PublicationService publicationService;
    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public Commentaire addComment(@RequestParam Map<Object,Object> body){
        Commentaire commentaire = Commentaire.builder()
                .utilisateur(this.userService.getUserById(Long.parseLong(body.get("utilisateur_id").toString())))
                .publication(this.publicationService.getPublicationById(Long.parseLong(body.get("publication_id").toString())))
                .post(body.get("post").toString())
                .dateAjout(LocalDate.now())
                .build();
        return this.commentaireService.addComment(commentaire);
    }
    @GetMapping("/{pubId}")
    public List<Comm> getCommentsByPubId(@PathVariable Long pubId){
        return this.commentaireService.getCommentsByPubId(pubId);
    }
    @GetMapping("/commentaire/{id}")
    public Commentaire getCommentById(@PathVariable Long id){
        return this.commentaireService.getCommentById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteCommentById(@PathVariable Long id){
        this.commentaireService.deleteCommentById(id);
    }
    @GetMapping("/comments")
    public List<Commentaire> getAll(){
        return this.commentaireService.getAll();
    }
}

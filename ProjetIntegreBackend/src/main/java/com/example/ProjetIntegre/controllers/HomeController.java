package com.example.ProjetIntegre.controllers;

import com.example.ProjetIntegre.DTO.Ann;
import com.example.ProjetIntegre.DTO.Pub;
import com.example.ProjetIntegre.DTO.SignInRequest;
import com.example.ProjetIntegre.entities.*;
import com.example.ProjetIntegre.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/public")
public class HomeController {
    public static final String ImageFolderProfilesPath = "C:/Users/Riane Mounir/Desktop/TourismPlatform/ImagesApp/";
    @Autowired
    private PublicationService publicationService;
    @Autowired
    private AnonnceService anonnceService;
    @Autowired
    private OrganisationService organisationService;
    @Autowired
    private TouristeService touristeService;
    @Autowired
    private UserService userService;
    @GetMapping("/home")
    public List<Ann> getAllAnonnce(){
        List<Ann> pubs = new ArrayList<>();
        for(Anonnce anonnce : this.anonnceService.getAllAnonnces()){
            pubs.add(new Ann(anonnce.getOrganisation(),anonnce));
        }
        return pubs;    }
    @GetMapping("/explore")
    public List<Pub> getAllFeeds(){
        List<Pub> pubs = new ArrayList<>();
        for(Publication publication : this.publicationService.getAllFeeds()){
            pubs.add(new Pub(publication.getUtilisateur(),publication));
        }
        return pubs;
    }
    /*@GetMapping("/anonnces/{theme}")
    public List<Anonnce> getAnonnceByTheme(@PathVariable String theme){
        return this.anonnceService.getAllAnonnceByTheme(theme);
    }
    */
    @PostMapping("/signUp")
    public ResponseEntity<Utilisateur> signUpPro(@RequestParam Map<Object, Object> body,
                                            @RequestParam("file") MultipartFile profilePhoto) {
        Utilisateur utilisateur = Utilisateur.builder()
                .username(body.get("nom").toString())
                .email(body.get("email").toString())
                .password(body.get("password").toString())
                .mobile(body.get("mobile").toString())
                .adress(body.get("adress").toString())
                .isPro(Boolean.parseBoolean((String) body.get("isPro")))
                .build();
        if (utilisateur.getIsPro()) {
            Organisation organisation = new Organisation();
            organisation.setId(utilisateur.getId());
            organisation.setUsername(utilisateur.getUsername());
            organisation.setEmail(utilisateur.getEmail());
            organisation.setMobile(utilisateur.getMobile());
            organisation.setPassword(utilisateur.getPassword());
            organisation.setIsPro(utilisateur.getIsPro());
            organisation.setAdress(utilisateur.getAdress());
            Category category = Category.valueOf(body.get("category").toString());
            organisation.setCategory(category);
            organisation.setNom(body.get("nom").toString());

            String result = this.organisationService.add(organisation, profilePhoto);
            if ("email used".equals(result)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            } else {
                 utilisateur = this.userService.getUser(organisation.getEmail());
                return ResponseEntity.status(HttpStatus.CREATED).body(utilisateur);
            }
        } else {
            Touriste touriste = new Touriste();
            touriste.setId(utilisateur.getId());
            touriste.setUsername(utilisateur.getUsername());
            touriste.setEmail(utilisateur.getEmail());
            touriste.setMobile(utilisateur.getMobile());
            touriste.setPassword(utilisateur.getPassword());
            touriste.setIsPro(utilisateur.getIsPro());
            touriste.setFirstName(body.get("firstName").toString());
            touriste.setLastName(body.get("lastName").toString());
            String result;
            if(profilePhoto==null){
                 result= this.touristeService.add(touriste,null);
            }
            else {
                result = this.touristeService.add(touriste, profilePhoto);
            }
            if ("email used".equals(result)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            } else {
                utilisateur = this.userService.getUser(touriste.getEmail());
                return ResponseEntity.status(HttpStatus.CREATED).body(utilisateur);
            }
        }
    }
    @PostMapping("/signIn")
    public Utilisateur signIn(@RequestBody SignInRequest signInRequest) {

        if (this.userService.verify(signInRequest.getUsername(), signInRequest.getPassword())) {
            Utilisateur utilisateur = this.userService.getUser(signInRequest.getUsername());
            if(utilisateur.getIsPro()){
                return this.organisationService.getOrganisationById(utilisateur.getId());
            }
            else {
                return this.touristeService.getTouristeById(utilisateur.getId());
            }
        }
        return null;
    }
}

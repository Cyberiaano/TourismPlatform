package com.example.ProjetIntegre.controllers;

import com.example.ProjetIntegre.entities.Organisation;
import com.example.ProjetIntegre.entities.Utilisateur;
import com.example.ProjetIntegre.services.OrganisationService;
import com.example.ProjetIntegre.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrganisationService organisationService;

    @PostMapping("/new")
    public Utilisateur addUser(@RequestBody Utilisateur utilisateur){
        return this.userService.addUser(utilisateur);
    }
    @GetMapping("/user/{id}")
    public Utilisateur getUserById(@PathVariable Long id){
        return this.userService.getUserById(id);
    }
    @DeleteMapping("/deleteUser/{id}")
    public void deleteUserById(@PathVariable Long id){
         this.userService.deleteUserById(id);
    }
    @PutMapping("updateUser/{id}")
        public Utilisateur updateUserById(@PathVariable Long id){
            return this.userService.updateUserById(id);
        }

    @GetMapping("/allUsers")
    public List<Utilisateur> getUsers(){
        return this.userService.getUsers();
    }
    @GetMapping("/organisations")
    public List<Organisation> getAllOrgs(){
        return this.organisationService.getALL();
    }
}

package com.example.ProjetIntegre;

import com.example.ProjetIntegre.entities.*;
import com.example.ProjetIntegre.services.FavoriteService;
import com.example.ProjetIntegre.services.OrganisationService;
import com.example.ProjetIntegre.services.PublicationService;
import com.example.ProjetIntegre.services.UserService;
import org.apache.catalina.User;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ProjetIntegreApplication {

	@Autowired
	private PublicationService publicationService;
	@Autowired
	private UserService userService;
	@Autowired
	private FavoriteService favoriteService;
	@Autowired
	private OrganisationService organisationService;
	public static void main(String[] args) {
		SpringApplication.run(ProjetIntegreApplication.class, args);
	}
	/*
	@Bean
	public  CommandLineRunner run() {
		return args -> {
			Utilisateur utilisateur =Utilisateur.builder()
					.email("riano")
					.isPro(true)
					.password("hardoz")
					.pathProfil("kharwan")
					.mobile("9237548917").build();
			Organisation organisation = (Organisation) utilisateur;
			organisation.setCategory(Category.CAFFE);
			Utilisateur utilisateur1 =Utilisateur.builder()
					.email("rianos")
					.isPro(true)
					.password("hardoz")
					.pathProfil("kharwan")
					.mobile("9237548917").build();
			Organisation organisation2 = (Organisation) utilisateur1;
			organisation2.setCategory(Category.HOTEL);
			List<String> c = new ArrayList<>();
			List<String> d = new ArrayList<>();
			d.add("kalifornia");
			d.add("savalj");
			d.add("kafbi");
			c.add("khalivdioa");
			c.add("rabooot");
			c.add("lakhabooot");
			Publication publication = Publication.builder()
					.post("this is my hotel")
					.utilisateur(organisation2)
					.dateAjout(LocalDate.now())
					.imagePaths(d)
					.build();
			Publication publication1 = Publication.builder()
					.post("this is my hotel1")
					.utilisateur(organisation2)
					.dateAjout(LocalDate.now())
					.imagePaths(d)
					.build();
			Publication publication2 = Publication.builder()
					.post("this is my caffe")
					.utilisateur(organisation)
					.dateAjout(LocalDate.now())
					.imagePaths(c)
					.build();
			Publication publication3 = Publication.builder()
					.post("this is my caffe1")
					.utilisateur(organisation)
					.dateAjout(LocalDate.now())
					.imagePaths(c)
					.build();
			List<Publication> publications = new ArrayList<>();
			List<Publication> publications1 = new ArrayList<>();

			publications.add(publication);
			publications.add(publication1);
			publications1.add(publication2);
			publications1.add(publication3);
			organisation2.setPublications(publications);
			organisation.setPublications(publications1);
			this.userService.addUser(organisation2);
			this.userService.addUser(organisation);
			this.organisationService.addOrg(organisation2);
			this.organisationService.addOrg(organisation);
			this.publicationService.addPublication(publication1);
			this.publicationService.addPublication(publication);
			this.publicationService.addPublication(publication2);
			this.publicationService.addPublication(publication3);
			this.favoriteService.addPublicationToFavorites(utilisateur1.getId(),publication2.getId());
			this.favoriteService.addPublicationToFavorites(utilisateur1.getId(),publication3.getId());
			this.favoriteService.addPublicationToFavorites(utilisateur.getId(),publication.getId());
			this.favoriteService.addPublicationToFavorites(utilisateur.getId(),publication1.getId());
		};

	}*/

}

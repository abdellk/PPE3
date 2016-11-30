package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modele.Action;
import modele.Roles;
import modele.Roles_action;
import modele.Roles_utilisateurs;
import modele.Utilisateurs;

public class Main {

	public static void main(String[] args) throws ParseException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jdbc");
		EntityManager em = emf.createEntityManager();
		
		SimpleDateFormat sdf = new SimpleDateFormat("y-M-d");
		
		Utilisateurs u1 = new Utilisateurs("soufian.a@free.fr", "AIT TIRITE", "SOUFIAN", new Date(), "test", "soufian AIT TIRITE");
		Utilisateurs u2 = new Utilisateurs("steven@blabla.fr", "DE CARVALHO", "STEVEN", new Date(), "test", "soufian AIT TIRITE");
		
		Action ac1 = new Action("modifier", new Date(), "Soufian AIT TIRITE", "droit de modification");
		Action ac2 = new Action("ajouter", new Date(), "Soufian AIT TIRITE", "droit d'ajout");
		Action ac3 = new Action("supprimer", new Date(), "Soufian AIT TIRITE", "droit de suppression");
		Action ac4 = new Action("lire", new Date(), "Soufian AIT TIRITE", "droit de lecture");
		
		Roles administrateur = new Roles("Administrateur", new Date(), "Soufian AIT TIRITE", "TOUS LES DROITS");
		Roles particulier = new Roles("Particulier", new Date(), "Soufian AIT TIRITE", "peut lire, ajouter et modifier des commandes");
		Roles magasin = new Roles("Magasin", new Date(), "Soufian AIT TIRITE", "peut lire, ajouter modifier ou supprimer les commandes");
		/*
		Roles_action admin1 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE", ac1, administrateur);
		Roles_action admin2 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE", ac2, administrateur);
		Roles_action admin3 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE", ac3, administrateur);
		Roles_action admin4 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE", ac4, administrateur);		

		Roles_action particulier1 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE", ac2, magasinier);
		Roles_action particulier2 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE", ac1, magasinier);
		Roles_action particulier3 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE", ac4, magasinier);
		
		Roles_action magasin1 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE", ac3, magasin);
		Roles_action magasin2 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE", ac1, magasin);
		Roles_action magasin3 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE", ac4, magasin);
		
		Roles_utilisateurs soufian_admin= new Roles_utilisateurs(sdf.parse("2016-11-09"), "Soufian AIT TIRITE", u1, administrateur);
		Roles_utilisateurs steven_magasin= new Roles_utilisateurs(sdf.parse("2016-11-09"), "Soufian AIT TIRITE", u2, magasin);
		*/
		Roles_utilisateurs soufian_admin= new Roles_utilisateurs(new Date(), "Soufian AIT TIRITE");
		u1.ajoutRoles(soufian_admin);
		administrateur.ajoutUtilisateur(soufian_admin);

		Roles_utilisateurs steven_magasin= new Roles_utilisateurs(new Date(), "Soufian AIT TIRITE");
		magasin.ajoutUtilisateur(steven_magasin);
		u2.ajoutRoles(steven_magasin);
		
		Roles_action admin1 = new Roles_action(new Date(), "Soufian AIT TIRITE");
		Roles_action admin2 = new Roles_action(new Date(), "Soufian AIT TIRITE");
		Roles_action admin3 = new Roles_action(new Date(), "Soufian AIT TIRITE");
		Roles_action admin4 = new Roles_action(new Date(), "Soufian AIT TIRITE");
		
		ac1.ajoutRole(admin1);
		administrateur.ajoutAction(admin1);
		ac2.ajoutRole(admin2);
		administrateur.ajoutAction(admin2);
		ac3.ajoutRole(admin3);
		administrateur.ajoutAction(admin3);
		ac4.ajoutRole(admin4);
		administrateur.ajoutAction(admin4);
		
		Roles_action particulier1 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE");
		Roles_action particulier2 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE");
		Roles_action particulier3 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE");
		
		ac2.ajoutRole(particulier1);
		particulier.ajoutAction(particulier1);
		ac1.ajoutRole(particulier2);
		particulier.ajoutAction(particulier2);
		ac4.ajoutRole(particulier3);
		particulier.ajoutAction(particulier3);
		
		Roles_action magasin1 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE");
		Roles_action magasin2 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE");
		Roles_action magasin3 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE");
		
		ac3.ajoutRole(magasin1);
		magasin.ajoutAction(magasin1);
		ac1.ajoutRole(magasin2);
		magasin.ajoutAction(magasin2);
		ac4.ajoutRole(magasin3);
		magasin.ajoutAction(magasin3);
		
		
		em.getTransaction().begin();
		
		em.persist(u1);
		em.persist(u2);
		
		em.persist(ac1);
		em.persist(ac2);
		em.persist(ac3);
		em.persist(ac4);
		
		em.persist(administrateur);
		em.persist(particulier);
		em.persist(magasin);
		
		em.persist(admin1);
		em.persist(admin2);
		em.persist(admin3);
		em.persist(admin4);
		
		em.persist(particulier1);
		em.persist(particulier2);
		em.persist(particulier3);
		
		em.persist(magasin1);
		em.persist(magasin2);
		em.persist(magasin3);
		
		em.persist(soufian_admin);
		em.persist(steven_magasin);
	
		em.getTransaction().commit();
		em.close();
	}

}

package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import controleur.Action;
import controleur.Roles;
import controleur.Roles_action;
import controleur.Roles_utilisateurs;
import controleur.Utilisateurs;

public class Main {

	public static void main(String[] args) throws ParseException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jdbc");
		EntityManager em = emf.createEntityManager();
		
		SimpleDateFormat sdf = new SimpleDateFormat("y-M-d");
		
		Utilisateurs u1 = new Utilisateurs("soufian.a@free.fr", "AIT TIRITE", "SOUFIAN", new Date(), "soufian AIT TIRITE");
		Utilisateurs u2 = new Utilisateurs("steven@blabla.fr", "DE CARVALHO", "STEVEN", sdf.parse("2016-11-09"), "soufian AIT TIRITE");
		
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
		soufian_admin.setRole(administrateur);
		soufian_admin.setUtilisateur(u1);

		Roles_utilisateurs steven_magasin= new Roles_utilisateurs(new Date(), "Soufian AIT TIRITE");
		steven_magasin.setRole(magasin);
		steven_magasin.setUtilisateur(u2);
		
		Roles_action admin1 = new Roles_action(new Date(), "Soufian AIT TIRITE");
		Roles_action admin2 = new Roles_action(new Date(), "Soufian AIT TIRITE");
		Roles_action admin3 = new Roles_action(new Date(), "Soufian AIT TIRITE");
		Roles_action admin4 = new Roles_action(new Date(), "Soufian AIT TIRITE");
		
		admin1.setAction(ac1);
		admin1.setRole(administrateur);
		admin2.setAction(ac2);
		admin2.setRole(administrateur);
		admin3.setAction(ac3);
		admin3.setRole(administrateur);
		admin4.setAction(ac4);
		admin4.setRole(administrateur);
		
		Roles_action particulier1 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE");
		Roles_action particulier2 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE");
		Roles_action particulier3 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE");
		
		particulier1.setAction(ac2);
		particulier1.setRole(particulier);
		particulier2.setAction(ac1);
		particulier2.setRole(particulier);
		particulier3.setAction(ac4);
		particulier3.setRole(particulier);
		
		Roles_action magasin1 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE");
		Roles_action magasin2 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE");
		Roles_action magasin3 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE");
		
		magasin1.setAction(ac3);
		magasin1.setRole(magasin);
		magasin2.setAction(ac1);
		magasin2.setRole(magasin);
		magasin3.setAction(ac4);
		magasin3.setRole(magasin);
		
		
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

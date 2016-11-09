package test;

import java.sql.Timestamp;
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
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA");
		EntityManager em = emf.createEntityManager();
		
		SimpleDateFormat sdf = new SimpleDateFormat("y-M-d");
		
		Utilisateurs u1 = new Utilisateurs("soufian.a@free.fr", "AIT TIRITE", "SOUFIAN", sdf.parse("2016-11-09"), "soufian AIT TIRITE");
		Utilisateurs u2 = new Utilisateurs("steven@blabla.fr", "DE CARVALHO", "STEVEN", sdf.parse("2016-11-09"), "soufian AIT TIRITE");
		
		Action ac1 = new Action("modifier", sdf.parse("2016-11-09"), "Soufian AIT TIRITE", "droit de modification");
		Action ac2 = new Action("ajouter", sdf.parse("2016-11-09"), "Soufian AIT TIRITE", "droit d'ajout");
		Action ac3 = new Action("supprimer", sdf.parse("2016-11-09"), "Soufian AIT TIRITE", "droit de suppression");
		Action ac4 = new Action("lire", sdf.parse("2016-11-09"), "Soufian AIT TIRITE", "droit de lecture");
		
		Roles administrateur = new Roles("Administrateur", sdf.parse("2016-11-09"), "Soufian AIT TIRITE", "TOUS LES DROITS");
		Roles magasinier = new Roles("Magasinier", sdf.parse("2016-11-09"), "Soufian AIT TIRITE", "peut lire, ajouter et modifier des commandes");
		Roles commercial = new Roles("Commercial", sdf.parse("2016-11-09"), "Soufian AIT TIRITE", "peut lire, ajouter modifier ou supprimer les commandes");
		
		Roles_action admin1 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE", ac1, administrateur);
		Roles_action admin2 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE", ac2, administrateur);
		Roles_action admin3 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE", ac3, administrateur);
		Roles_action admin4 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE", ac4, administrateur);		

		Roles_action magasinier1 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE", ac2, magasinier);
		Roles_action magasinier2 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE", ac1, magasinier);
		Roles_action magasinier3 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE", ac4, magasinier);
		
		Roles_action commercial1 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE", ac3, commercial);
		Roles_action commercial2 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE", ac1, commercial);
		Roles_action commercial3 = new Roles_action(sdf.parse("2016-11-09"), "Soufian AIT TIRITE", ac4, commercial);
		
		Roles_utilisateurs soufian_admin= new Roles_utilisateurs(sdf.parse("2016-11-09"), "Soufian AIT TIRITE", u1, administrateur);
		Roles_utilisateurs steven_commercial= new Roles_utilisateurs(sdf.parse("2016-11-09"), "Soufian AIT TIRITE", u2, commercial);
		
		em.getTransaction().begin();
		
		em.persist(u1);
		em.persist(u2);
		
		em.persist(ac1);
		em.persist(ac2);
		em.persist(ac3);
		em.persist(ac4);
		
		em.persist(administrateur);
		em.persist(magasinier);
		em.persist(commercial);
		
		em.persist(admin1);
		em.persist(admin2);
		em.persist(admin3);
		em.persist(admin4);

		em.persist(magasinier1);
		em.persist(magasinier2);
		em.persist(magasinier3);

		em.persist(commercial1);
		em.persist(commercial2);
		em.persist(commercial3);
		
		em.persist(soufian_admin);
		em.persist(steven_commercial);
		
		em.getTransaction().commit();
		em.close();
	}

}

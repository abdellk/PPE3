package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modele2.Action;
import modele2.Roles;
import modele2.Roles_action;
import modele2.Roles_utilisateurs;
import modele2.Utilisateurs;

public class Main {

	public static void main(String[] args) throws ParseException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jdbc");
		EntityManager em = emf.createEntityManager();
		
		SimpleDateFormat sdf = new SimpleDateFormat("y-M-d");
		
		Utilisateurs soufian = new Utilisateurs("soufian.a@free.fr", "AIT TIRITE", "SOUFIAN", new Date(), "test", "soufian AIT TIRITE");
		Utilisateurs dupont = new Utilisateurs("dupontc@blabla.fr", "DUPONT", "CHRISTOPHE", new Date(), "dupont00", "soufian AIT TIRITE");
		Utilisateurs dubois = new Utilisateurs("dubois@blabla.fr", "DUBOIS", "MANUEL", new Date(), "dubois11", "soufian AIT TIRITE");
		Utilisateurs deschamps = new Utilisateurs("deschamps@blabla.fr", "DESCHAMPS", "DIDIER", new Date(), "didier22", "soufian AIT TIRITE");
		
		Action rediger_annonce = new Action("Rédiger une annonce", new Date(), "Soufian AIT TIRITE", "droit de rédaction");
		Action modifier_annonce = new Action("Modifier une annonce", new Date(), "Soufian AIT TIRITE", "droit de modification");
		Action supprimer_annonce = new Action("Supprimer une annonce", new Date(), "Soufian AIT TIRITE", "droit de suppression");
		Action publier_annonce = new Action("Publier une annonce", new Date(), "Soufian AIT TIRITE", "droit de publication");
		Action vendre_guitare = new Action("Vendre une guitare", new Date(), "Soufian AIT TIRITE", "droit de vendre une guitare");
		Action gerer_magasin = new Action("Gérer les magasins", new Date(), "Soufian AIT TIRITE", "droit de gérer les magasin");
		Action gerer_acces = new Action("Gérer les accès", new Date(), "Soufian AIT TIRITE", "droit de gérer les accès");
		Action consulter_indicateurs = new Action("Consulter les indicateurs", new Date(), "Soufian AIT TIRITE", "droit de consulter les indicateurs");
		
		Roles administrateur = new Roles("Administrateur", new Date(), "Soufian AIT TIRITE", "TOUS LES DROITS");
		Roles annonceur = new Roles("Annonceur", new Date(), "Soufian AIT TIRITE", "TOUS LES DROITS");
		Roles magasin = new Roles("Magasin", new Date(), "Soufian AIT TIRITE", "TOUS LES DROITS");
		
		Roles_utilisateurs administrateur_soufian = new Roles_utilisateurs(new Date(), soufian.getNom()+" "+soufian.getPrenom());
		administrateur.ajoutUtilisateur(administrateur_soufian);
		soufian.ajoutRoles(administrateur_soufian);		
		Roles_utilisateurs annonceur_dupont = new Roles_utilisateurs(new Date(), soufian.getNom()+" "+soufian.getPrenom());
		annonceur.ajoutUtilisateur(annonceur_dupont);
		dupont.ajoutRoles(annonceur_dupont);
		Roles_utilisateurs magasin_dubois = new Roles_utilisateurs(new Date(), soufian.getNom()+" "+soufian.getPrenom());
		magasin.ajoutUtilisateur(magasin_dubois);
		dubois.ajoutRoles(magasin_dubois);
		Roles_utilisateurs deschamps_administrateur = new Roles_utilisateurs(new Date(), soufian.getNom()+" "+soufian.getPrenom());
		deschamps.ajoutRoles(deschamps_administrateur);
		administrateur.ajoutUtilisateur(deschamps_administrateur);
		Roles_utilisateurs deschamps_annonceur = new Roles_utilisateurs(new Date(), soufian.getNom()+" "+soufian.getPrenom());
		deschamps.ajoutRoles(deschamps_annonceur);
		annonceur.ajoutUtilisateur(deschamps_annonceur);
		
		Roles_action annonceur_rediger = new Roles_action(new Date(), soufian.getNom()+" "+soufian.getPrenom());
		annonceur.ajoutAction(annonceur_rediger);
		rediger_annonce.ajoutRole(annonceur_rediger);
		Roles_action annonceur_modifier = new Roles_action(new Date(), soufian.getNom()+" "+soufian.getPrenom());
		annonceur.ajoutAction(annonceur_modifier);
		modifier_annonce.ajoutRole(annonceur_modifier);
		Roles_action annonceur_supprimer = new Roles_action(new Date(), soufian.getNom()+" "+soufian.getPrenom());
		annonceur.ajoutAction(annonceur_supprimer);
		supprimer_annonce.ajoutRole(annonceur_supprimer);
		
		Roles_action magasin_rediger = new Roles_action(new Date(), soufian.getNom()+" "+soufian.getPrenom());
		magasin.ajoutAction(magasin_rediger);
		rediger_annonce.ajoutRole(magasin_rediger);
		Roles_action magasin_modifier = new Roles_action(new Date(), soufian.getNom()+" "+soufian.getPrenom());
		magasin.ajoutAction(magasin_modifier);
		modifier_annonce.ajoutRole(magasin_modifier);
		Roles_action magasin_supprimer = new Roles_action(new Date(), soufian.getNom()+" "+soufian.getPrenom());
		magasin.ajoutAction(magasin_supprimer);
		supprimer_annonce.ajoutRole(magasin_supprimer);
		Roles_action magasin_publier = new Roles_action(new Date(), soufian.getNom()+" "+soufian.getPrenom());
		magasin.ajoutAction(magasin_publier);
		publier_annonce.ajoutRole(magasin_publier);
		Roles_action magasin_vendre_guitare = new Roles_action(new Date(), soufian.getNom()+" "+soufian.getPrenom());
		magasin.ajoutAction(magasin_vendre_guitare);
		vendre_guitare.ajoutRole(magasin_vendre_guitare);
		
		Roles_action administrateur_gerer_magasin = new Roles_action(new Date(), soufian.getNom()+" "+soufian.getPrenom());
		administrateur.ajoutAction(administrateur_gerer_magasin);
		gerer_magasin.ajoutRole(administrateur_gerer_magasin);
		Roles_action administrateur_gerer_acces = new Roles_action(new Date(), soufian.getNom()+" "+soufian.getPrenom());
		administrateur.ajoutAction(administrateur_gerer_acces);
		gerer_acces.ajoutRole(administrateur_gerer_acces);
		Roles_action administrateur_consulter_indicateurs = new Roles_action(new Date(), soufian.getNom()+" "+soufian.getPrenom());
		administrateur.ajoutAction(administrateur_consulter_indicateurs);
		consulter_indicateurs.ajoutRole(administrateur_consulter_indicateurs);
		
		
		
		em.getTransaction().begin();
		
		em.persist(administrateur);
		em.persist(magasin);
		em.persist(annonceur);
		
		em.persist(soufian);
		em.persist(dubois);
		em.persist(dupont);
		em.persist(deschamps);
		
		em.persist(rediger_annonce);
		em.persist(modifier_annonce);
		em.persist(supprimer_annonce);
		em.persist(publier_annonce);
		em.persist(vendre_guitare);
		em.persist(gerer_acces);
		em.persist(gerer_magasin);
		em.persist(consulter_indicateurs);
	
		em.getTransaction().commit();
		em.close();
	}

}

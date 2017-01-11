package outils;

import java.util.List;

import javax.persistence.EntityManager;

import modele.Utilisateur;
import ressources.FournisseurDePersistance;

public class Resultat {

	private List<Utilisateur> employees;//liste des utilisateurs à faire persiter

	public Resultat() {
		super();
	}

	public void setEmployees(List<Utilisateur> employees) {
		this.employees = employees;
	}

	public List<Utilisateur> getEmployees() {
		return employees;
	}
	
	public void enregistrer() {
		EntityManager em = FournisseurDePersistance.getInstance().fournir();//connexion à la base de donnée
		em.getTransaction().begin();
		System.out.println("TAILLE : " + employees.size());
		for(Utilisateur obj : employees) {
			System.out.println(obj);
			em.persist(obj);//persistence de chacun des utilisateurs contenus dans la collection "employees"
		}
		em.getTransaction().commit();
		em.close();
		FournisseurDePersistance.getInstance().detruire();
	}
}

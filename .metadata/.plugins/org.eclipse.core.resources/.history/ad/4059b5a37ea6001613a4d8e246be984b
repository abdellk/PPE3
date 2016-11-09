package controleur;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="roles_utilisateurs")
public class Roles_utilisateurs {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private Date derniere_mise_a_jour;
	private String modifier_par;
	@ManyToOne
	@JoinColumn(name="utlisateurs_fk")
	private Utilisateurs utilisateur;
	
	public Roles_utilisateurs(Date derniere_mise_a_jour, String modifier_par, Utilisateurs utilisateur) {
		super();
		this.derniere_mise_a_jour = derniere_mise_a_jour;
		this.modifier_par = modifier_par;
		this.utilisateur = utilisateur;
	}

	public Roles_utilisateurs() {
		super();
	}

	public Date getDerniere_mise_a_jour() {
		return derniere_mise_a_jour;
	}

	public void setDerniere_mise_a_jour(Date derniere_mise_a_jour) {
		this.derniere_mise_a_jour = derniere_mise_a_jour;
	}

	public String getModifier_par() {
		return modifier_par;
	}

	public void setModifier_par(String modifier_par) {
		this.modifier_par = modifier_par;
	}

	public int getId() {
		return id;
	}

	public Utilisateurs getUtilisateur() {
		return utilisateur;
	}

	@Override
	public String toString() {
		return "Roles_utilisateurs [id=" + id + ", derniere_mise_a_jour=" + derniere_mise_a_jour + ", modifier_par="
				+ modifier_par + ", utilisateur=" + utilisateur + "]";
	}
	
	
}

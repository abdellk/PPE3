package modele;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="roles_utilisateurs")
public class Roles_utilisateurs {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idru;
	@Temporal(TemporalType.TIMESTAMP)
	private Date derniere_mise_a_jour;
	private String modifier_par;
	@ManyToOne
	@JoinColumn(name="utlisateurs_fk")
	private Utilisateurs utilisateur;
	@ManyToOne
	@JoinColumn(name="roles_fk")
	private Roles role;

	/*public Roles_utilisateurs(Date derniere_mise_a_jour, String modifier_par, Utilisateurs utilisateur, Roles role) {
		super();
		this.derniere_mise_a_jour = derniere_mise_a_jour;
		this.modifier_par = modifier_par;
		this.utilisateur = utilisateur;
		this.role = role;
	}*/
	public Roles_utilisateurs(Date derniere_mise_a_jour, String modifier_par) {
		super();
		this.derniere_mise_a_jour = derniere_mise_a_jour;
		this.modifier_par = modifier_par;
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
		return idru;
	}

	public Utilisateurs getUtilisateur() {
		return utilisateur;
	}

	public Roles getRole() {
		return role;
	}

	public void setUtilisateur(Utilisateurs utilisateur) {
		this.utilisateur = utilisateur;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Roles_utilisateurs [idru=" + idru + ", derniere_mise_a_jour=" + derniere_mise_a_jour + ", modifier_par="
				+ modifier_par + ", utilisateur=" + utilisateur + ", role=" + role + "]";
	}
	
}

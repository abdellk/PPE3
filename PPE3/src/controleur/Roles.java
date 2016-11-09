package controleur;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="roles")
public class Roles {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idr;
	private String role;
	@Temporal(TemporalType.TIMESTAMP)
	private Date derniere_mise_a_jour;
	private String modifier_par;
	private String description;
	@OneToMany(mappedBy="role", cascade=CascadeType.ALL)
	private List<Roles_utilisateurs> utilisateurs = new ArrayList<>();
	
	public Roles(String role, Date derniere_mise_a_jour, String modifier_par, String description) {
		super();
		this.role = role;
		this.derniere_mise_a_jour = derniere_mise_a_jour;
		this.modifier_par = modifier_par;
		this.description = description;
	}

	public Roles() {
		super();
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return idr;
	}

	public List<Roles_utilisateurs> getUtilisateurs() {
		return utilisateurs;
	}

	@Override
	public String toString() {
		return "Roles [idr=" + idr + ", role=" + role + ", derniere_mise_a_jour=" + derniere_mise_a_jour
				+ ", modifier_par=" + modifier_par + ", description=" + description + ", utilisateurs=" + utilisateurs
				+ "]";
	}
	
	
	
}

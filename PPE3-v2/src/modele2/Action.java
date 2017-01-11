package modele2;

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
@Table(name="action")
public class Action {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ida;
	private String action;
	@Temporal(TemporalType.TIMESTAMP)
	private Date derniere_mise_a_jour;
	private String modifier_par;
	private String description;
	@OneToMany(mappedBy="action", cascade=CascadeType.ALL)
	private List<Roles_action> actions = new ArrayList<Roles_action>();
	
	public Action(String action, Date derniere_mise_a_jour, String modifier_par, String description) {
		super();
		this.action = action;
		this.derniere_mise_a_jour = derniere_mise_a_jour;
		this.modifier_par = modifier_par;
		this.description = description;
	}
	
	public void ajoutRole(Roles_action ra){
		actions.add(ra);
		ra.setAction(this);
	}

	public Action() {
		super();
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
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
		return ida;
	}

	@Override
	public String toString() {
		return "Action [id=" + ida + ", action=" + action + ", derniere_mise_a_jour=" + derniere_mise_a_jour
				+ ", modifier_par=" + modifier_par + ", description=" + description + "]";
	}
	
	
	
}

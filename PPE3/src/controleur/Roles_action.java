package controleur;

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
@Table(name="roles_action")
public class Roles_action {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idra;
	@Temporal(TemporalType.TIMESTAMP)
	private Date derniere_mise_a_jour;
	private String modifier_par;
	@ManyToOne
	@JoinColumn(name="action_fk")
	private Action action;
	@ManyToOne
	@JoinColumn(name="role_fk")
	private Roles role;
	
	public Roles_action(Date derniere_mise_a_jour, String modifier_par, Action action, Roles role) {
		super();
		this.derniere_mise_a_jour = derniere_mise_a_jour;
		this.modifier_par = modifier_par;
		this.action = action;
		this.role = role;
	}

	public Roles_action() {
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

	public Action getAction() {
		return action;
	}

	public Roles getRole() {
		return role;
	}

	public int getIdra() {
		return idra;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public void setRole(Roles role) {
		this.role = role;
	}
	
	
}

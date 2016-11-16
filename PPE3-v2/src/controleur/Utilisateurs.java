package controleur;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="utilisateurs")
@ManagedBean(name="utilisateurs")
@RequestScoped
public class Utilisateurs {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idut;
	private String email;
	private String nom;
	private String prenom;
	@Temporal(TemporalType.TIMESTAMP)
	private Date derniere_mise_a_jour;
	private String modifier_par;
	@OneToMany(mappedBy="utilisateur", cascade=CascadeType.ALL)
	private List<Roles_utilisateurs> roles = new ArrayList<Roles_utilisateurs>();
	@Transient
	private String message;
	private String password;
	@Transient
	@ManagedProperty(value="#{fournisseur}")
	private GerantPersistence fournisseur;
	
	public void ajoutRoles(Roles_utilisateurs ru){
		roles.add(ru);
		ru.setUtilisateur(this);
	}
	
	public Utilisateurs(String email, String nom, String prenom, Date derniere_mise_a_jour,
			String password, String modifier_par) {
		super();
		this.email = email;
		this.nom = nom;
		this.prenom = prenom;
		this.derniere_mise_a_jour = derniere_mise_a_jour;
		this.password = password;
		this.modifier_par = modifier_par;
	}

	public void Connexion(){
		this.message=" ";
		EntityManager em = fournisseur.fournir();
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Utilisateurs> resultat = em.createNativeQuery("SELECT * FROM UTILISATEURS WHERE PRENOM="+prenom+" AND PASSWORD = "+password ,Utilisateurs.class).getResultList();
		em.getTransaction().commit();
		em.close();
		
		if(resultat.isEmpty()){
			this.message="erreur de connexion";
		}else{
			for(Utilisateurs iter: resultat){
				this.message= "Bienvenue "+iter.getNom()+" "+iter.getPrenom();
			}
		}
	}
	
	public Utilisateurs() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public GerantPersistence getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(GerantPersistence fournisseur) {
		this.fournisseur = fournisseur;
	}

	public int getIdut() {
		return idut;
	}

	public List<Roles_utilisateurs> getRoles() {
		return roles;
	}
	
	

}

package controleur;


public class Utilisateur {
	
	private String user;
	private String nom;
	private String prenom;
	private String mdp;
	private String message;
	
	public Utilisateur() {
		super();
		this.user = "soufian";
		this.nom = "AIT TIRITE";
		this.prenom = "Soufian";
		this.mdp = "test";
		this.message = "Bienvenue sur l'application !";
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
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

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Utilisateur [user=" + user + ", nom=" + nom + ", prenom=" + prenom + ", mdp=" + mdp + ", message="
				+ message + "]";
	}
	
	
}

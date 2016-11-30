package controleur;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import controleur.MessageDTO;

@ManagedBean(name = "authentification")
@RequestScoped
public class Connexion {

	private String login;
	private String password;
	private String message;

	public Connexion() {
		super();
	}

	public Connexion(String login, String password, String message) {
		super();
		this.login = login;
		this.password = password;
		this.message = message;
	}

	public void connect(){
		Client client = ClientBuilder.newClient();
		WebTarget cible = client.target(UriBuilder.fromPath("http://tomcatserver:8080/restful-dto-jaxb"));
		WebTarget ciblefinale = cible.path("dto");
		MessageDTO dto = ciblefinale.queryParam("login", this.login).queryParam("password", this.password).request(MediaType.APPLICATION_XML).get(MessageDTO.class);
		this.message = dto.getBienvenue()+" "+dto.getRole();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
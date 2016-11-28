package client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import service.MessageDTO;

public class ClientREST {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		WebTarget cible = client.target(UriBuilder.fromPath("http://172.17.0.3:8080/restful-dto-jaxb"));
		WebTarget ciblefinale = cible.path("dto");
		MessageDTO dto = ciblefinale.queryParam("login", "Soufian").queryParam("password", "test").request(MediaType.APPLICATION_XML).get(MessageDTO.class);
		System.out.println(dto.getBienvenue()+" "+dto.getRole());
	}

}

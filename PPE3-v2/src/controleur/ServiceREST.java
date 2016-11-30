package controleur;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import modele.Utilisateurs;

@Path("dto")
public class ServiceREST {

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public MessageDTO getDTO(@QueryParam("login")String login, @QueryParam("password")String password){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jdbc");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		List<Utilisateurs> utilisateur = em.createNativeQuery("SELECT * FROM UTILISATEURS", Utilisateurs.class).getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		
		
		MessageDTO message = new MessageDTO("Bienvenue "+login, "Administrateur");
		return message;
	}
	
}

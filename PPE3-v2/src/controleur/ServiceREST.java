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

import modele.Roles_utilisateurs;
import modele.Utilisateurs;

@Path("dto")
public class ServiceREST {

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public MessageDTO getDTO(@QueryParam("login")String login, @QueryParam("password")String password){
		MessageDTO message=null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jdbc");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		List<Utilisateurs> utilisateur = em.createNativeQuery("SELECT * FROM UTILISATEURS", Utilisateurs.class).getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		for(Utilisateurs iter:utilisateur){
			if(iter.getEmail().equals(login)){
				if(iter.getPassword().equals(password)){
					String roles=null;
					for(Roles_utilisateurs iter2: iter.getRoles()){
						roles += iter2.getRole().getRole()+", ";
					}
					message=new MessageDTO(iter.getNom()+" "+iter.getPrenom(),roles);
				}
				else
					message=new MessageDTO("Erreur lors de la saisie du mot de passe !","");
			}
			else
				message=new MessageDTO("Email inconnue !","");
		}
		
		return message;
	}
	
}

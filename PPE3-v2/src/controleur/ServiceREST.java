package controleur;

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

@Path("/dto")
public class ServiceREST {
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public MessageDTO getDTO(@QueryParam("login")String login, @QueryParam("password")String password){
		MessageDTO message=null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jdbc");
		EntityManager em = emf.createEntityManager();
		try{
			em.getTransaction().begin();
			Utilisateurs user= (Utilisateurs) em.createNativeQuery("SELECT * FROM utilisateurs WHERE PRENOM='"+login+"'", Utilisateurs.class).getSingleResult();
			if(user.getPassword().equals(password)){
				String msg="Bienvenue ! "+user.getNom()+" "+user.getPrenom();
				String role = ", Rôle(s):\n";
				for(Roles_utilisateurs iter : user.getRoles()){
					role += iter.getRole().getRole()+"\n";
				}
				message = new MessageDTO(msg, role);
			}else{
				message= new MessageDTO("mauvais mot de passe !", "");
			}
		}catch(Exception e){
			e.printStackTrace();
			message= new MessageDTO("Email inconnue !", "");
		}
		finally {
			em.getTransaction().commit();
			em.close();
		}
		return message;
	}
	
}

package controleur;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import modele2.Roles_utilisateurs;
import modele2.Utilisateurs;

@Path("/dtoLATER")
public class ServiceREST {

	private final static String QUEUE_NAME = "journal";
	private String messageJournal;
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public MessageDTO getDTO(@QueryParam("login")String login, @QueryParam("password")String password){
		MessageDTO message=null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jdbc");
		EntityManager em = emf.createEntityManager();
		try{
			
			em.getTransaction().begin();
			Utilisateurs user= (Utilisateurs) em.createNativeQuery("SELECT * FROM utilisateurs WHERE EMAIL='"+login+"'", Utilisateurs.class).getSingleResult();
			
			String nomprenom = user.getNom() + " " + user.getPrenom();
			if(user.getPassword().equals(password)){
				String msg="Bienvenue ! "+ nomprenom;
				String role = ", Rôle(s):\n";
				for(Roles_utilisateurs iter : user.getRoles()){
					role += iter.getRole().getRole()+", \n";
				}
				message = new MessageDTO(msg, role);
				messageJournal = login + "|" + nomprenom + "|succes|" + new Date();
			}else{
				messageJournal = login + "|" + nomprenom + "|mauvais mot de passe|" + new Date();
				message = new MessageDTO("mauvais mot de passe", "");
			}
		}catch(Exception e){
			e.printStackTrace();
			message= new MessageDTO("Email inconnue !", "");
			messageJournal = login + "|null|utilisateur inconnu|" + new  Date();
		}
		finally {
			em.getTransaction().commit();
			em.close();
			try {
					journaliser();
			} catch (Exception e) {e.printStackTrace();
			}	
		}
		return message;
	}
	
	
	private void journaliser() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("rabbitmq");
	    Connection connexion = (Connection) factory.newConnection();
	    Channel channel = connexion.createChannel();
	    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	    channel.basicPublish("", QUEUE_NAME, null, messageJournal.getBytes());
	    System.out.println(" [x] Envoyé '" + messageJournal + "'");
	    channel.close();
	    connexion.close();
	}
	
}

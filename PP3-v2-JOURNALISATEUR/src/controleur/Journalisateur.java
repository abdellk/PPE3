package controleur;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.lang3.StringUtils;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import modele.Journal;

@ManagedBean(name="journalisateur", eager=true)
@ApplicationScoped
public class Journalisateur {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("journal");
	private final static String QUEUE_NAME = "journal";
	private String message;
	private Connection connection = null;
	private Channel channel = null;
	
	@PostConstruct
	public void initialisation() {
		try {
				consommer();
		} catch (Exception e) {	e.printStackTrace(); }
	}
	
	public void consommer() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("rabbitmq");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
	    channel.queueDeclare(QUEUE_NAME, false, false, false, null);	    
	    Consumer consumer = new DefaultConsumer(channel) {
	        @Override
	        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
	            throws IOException {
	          message = new String(body, "UTF-8");
	        }
	      };
	      channel.basicConsume(QUEUE_NAME, true, consumer);
	}
	
	private void journaliser() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		String donneesMembres[] = StringUtils.split(message, "|");
		Journal journal = new Journal();
		journal.setEmail(donneesMembres[0]);
		journal.setUtilisateur(donneesMembres[1]);
		System.out.println("STATU " + donneesMembres[2]);
		journal.setStatut(donneesMembres[2]);
		journal.setDateacces(new Date());		
		em.persist(journal);
		
		em.getTransaction().commit();
		em.close();
	}
	
	public Journalisateur() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}		
}

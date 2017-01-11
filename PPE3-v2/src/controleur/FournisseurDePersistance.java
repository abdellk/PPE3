package controleur;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ManagedBean
@ApplicationScoped
public class FournisseurDePersistance {
	
	public EntityManager fournir() {
		return emf.createEntityManager();
	}
	
	public static FournisseurDePersistance getInstance() {
		if(instance == null)
			instance = new FournisseurDePersistance();
		
		return instance;
	}
	
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("authentification");
	private static FournisseurDePersistance instance = null;

	private FournisseurDePersistance() {
		super();
	}

	public void detruire() {
		emf.close();
	}
	
}

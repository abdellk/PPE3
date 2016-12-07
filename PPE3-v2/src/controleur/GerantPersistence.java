package controleur;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ManagedBean(name="fournisseur", eager=true)
@RequestScoped
public class GerantPersistence {
	
	public EntityManager fournir() {
		return emf.createEntityManager();
	}
	
	public static GerantPersistence getInstance() {
		if(instance == null)
			instance = new GerantPersistence();
		
		return instance;
	}
	
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("authentification");
	private static GerantPersistence instance = null;

	private GerantPersistence() {
		super();
	}

	public void detruire() {
		emf.close();
	}
}

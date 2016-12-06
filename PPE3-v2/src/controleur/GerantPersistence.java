package controleur;

import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ManagedBean(name="fournisseur", eager=true)
@RequestScoped
public class GerantPersistence {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jdbc");
	
	public GerantPersistence() {
		super();
	}
	public EntityManagerFactory getEmf() {
		return emf;
	}
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	public EntityManager fournir() {
		return emf.createEntityManager();
	}
	@PreDestroy
	public void fin(){
		emf.close();
	}
}

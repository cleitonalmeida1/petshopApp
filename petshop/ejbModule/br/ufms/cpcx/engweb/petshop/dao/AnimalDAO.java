package br.ufms.cpcx.engweb.petshop.dao;



import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import br.ufms.cpcx.engweb.petshop.model.Animal;

@Stateless
public class AnimalDAO {
	@PersistenceContext
	private EntityManager em;

	
	public Animal persist(Animal t) {
		if (t.getId() != null) {
			return em.merge(t);
		}
		em.persist(t);
		return t;
	}

	
	@SuppressWarnings("unchecked")
	public List<Animal> listAll() {
		Query query = em.createQuery("SELECT a FROM Animal a");
		return (List<Animal>) query.getResultList();
	}

	
	public Animal findById(Long id) {
		return em.find(Animal.class, id);
	}

	
	public void removerAnimal(Animal animal) {
		em.remove(animal);
	}

	
	public void removerAnimal(Long id) {
		Query query = em.createQuery("DELETE FROM Animal a WHERE a.id = "+id);
		query.executeUpdate();
	}

}


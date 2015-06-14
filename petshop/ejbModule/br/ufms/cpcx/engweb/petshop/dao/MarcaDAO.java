package br.ufms.cpcx.engweb.petshop.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufms.cpcx.engweb.petshop.model.Marca;

@Stateless
public class MarcaDAO {
	@PersistenceContext
	private EntityManager em;

	public Marca persist(Marca t) {
		if (t.getId() != null) {
			return em.merge(t);
		}
		em.persist(t);
		return t;
	}

	@SuppressWarnings("unchecked")
	public List<Marca> listAll() {
		Query query = em.createQuery("SELECT a FROM Marca a");
		return (List<Marca>) query.getResultList();
	}

	public Marca findById(Long id) {
		return em.find(Marca.class, id);
	}

	public void removerMarca(Marca marca) {
		em.remove(marca);
	}

	public void removerMarca(Long id) {
		Query query = em.createQuery("DELETE FROM Marca a WHERE a.id = " + id);
		query.executeUpdate();
	}
}

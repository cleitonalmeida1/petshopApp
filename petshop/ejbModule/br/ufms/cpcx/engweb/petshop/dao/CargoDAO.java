package br.ufms.cpcx.engweb.petshop.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufms.cpcx.engweb.petshop.model.Cargo;

@Stateless
public class CargoDAO {
	@PersistenceContext
	private EntityManager em;

	public Cargo persist(Cargo t) {
		if (t.getId() != null) {
			return em.merge(t);
		}
		em.persist(t);
		return t;
	}

	@SuppressWarnings("unchecked")
	public List<Cargo> listAll() {
		Query query = em.createQuery("SELECT a FROM Cargo a");
		return (List<Cargo>) query.getResultList();
	}

	public Cargo findById(Long id) {
		return em.find(Cargo.class, id);
	}

	public void removerCargo(Cargo cargo) {
		em.remove(cargo);
	}

	public void removerCargo(Long id) {
		Query query = em.createQuery("DELETE FROM Cargo a WHERE a.id = " + id);
		query.executeUpdate();
	}
}

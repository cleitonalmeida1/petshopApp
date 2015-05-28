package br.ufms.cpcx.engweb.petshop.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufms.cpcx.engweb.petshop.model.Foto;


@Stateless
public class FotoDAO{
	@PersistenceContext
	private EntityManager em;

	public Foto persist(Foto t) {
		if (t.getId() != null) {
			return em.merge(t);
		}
		em.persist(t);
		return t;
	}

	public void delete(Long id) {
		Query query = em
				.createQuery("DELETE FROM Foto f WHERE f.id = " + id);
		query.executeUpdate();
	}

	public void delete(Foto t) {
		em.remove(t);
	}

	@SuppressWarnings("unchecked")
	public List<Foto> listAll() {
		Query query = em.createQuery("SELECT f FROM Foto f");
		return (List<Foto>) query.getResultList();
	}

	public Foto findById(Long id) {
		return em.find(Foto.class, id);
	}
}

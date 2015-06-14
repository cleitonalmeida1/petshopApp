package br.ufms.cpcx.engweb.petshop.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufms.cpcx.engweb.petshop.model.Pais;

@Stateless
public class PaisDAO {
	@PersistenceContext
	private EntityManager em;

	public Pais persist(Pais t) {
		if (t.getId() != null) {
			return em.merge(t);
		}
		em.persist(t);
		return t;
	}

	@SuppressWarnings("unchecked")
	public List<Pais> listAll() {
		Query query = em.createQuery("SELECT a FROM Pais a ORDER BY a.nome");
		return (List<Pais>) query.getResultList();
	}

	public Pais findById(Long id) {
		return em.find(Pais.class, id);
	}

	public void removerPais(Pais pais) {
		em.remove(pais);
	}

	public void removerPais(Long id) {
		Query query = em.createQuery("DELETE FROM Pais a WHERE a.id = " + id);
		query.executeUpdate();
	}

}

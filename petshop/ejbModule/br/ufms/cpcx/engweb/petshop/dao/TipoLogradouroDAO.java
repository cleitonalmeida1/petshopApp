package br.ufms.cpcx.engweb.petshop.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufms.cpcx.engweb.petshop.model.TipoLogradouro;

@Stateless
public class TipoLogradouroDAO {
	@PersistenceContext
	private EntityManager em;

	public TipoLogradouro persist(TipoLogradouro t) {
		if (t.getId() != null) {
			return em.merge(t);
		}
		em.persist(t);
		return t;
	}

	@SuppressWarnings("unchecked")
	public List<TipoLogradouro> listAll() {
		Query query = em.createQuery("SELECT a FROM TipoLogradouro a");
		return (List<TipoLogradouro>) query.getResultList();
	}

	public TipoLogradouro findById(Long id) {
		return em.find(TipoLogradouro.class, id);
	}

	public void removerTipoLogradouro(TipoLogradouro tipoLogradouro) {
		em.remove(tipoLogradouro);
	}

	public void removerTipoLogradouro(Long id) {
		Query query = em.createQuery("DELETE FROM Pais a WHERE a.id = " + id);
		query.executeUpdate();
	}

}

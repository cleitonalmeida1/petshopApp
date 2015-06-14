package br.ufms.cpcx.engweb.petshop.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufms.cpcx.engweb.petshop.model.Telefone;

@Stateless
public class TelefoneDAO {
	@PersistenceContext
	private EntityManager em;

	public Telefone persist(Telefone t) {
		if (t.getId() != null) {
			return em.merge(t);
		}
		em.persist(t);
		return t;
	}

	@SuppressWarnings("unchecked")
	public List<Telefone> listAll() {
		Query query = em.createQuery("SELECT a FROM Telefone a");
		return (List<Telefone>) query.getResultList();
	}

	public Telefone findById(Long id) {
		return em.find(Telefone.class, id);
	}

	public void removerTelefone(Telefone telefone) {
		em.remove(telefone);
	}

	public void removerTelefone(Long id) {
		Query query = em.createQuery("DELETE FROM Telefone a WHERE a.id = " + id);
		query.executeUpdate();
	}

}

package br.ufms.cpcx.engweb.petshop.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufms.cpcx.engweb.petshop.model.Endereco;

@Stateless
public class EnderecoDAO {
	@PersistenceContext
	private EntityManager em;

	public Endereco persist(Endereco t) {
		if (t.getId() != null) {
			return em.merge(t);
		}
		em.persist(t);
		return t;
	}

	@SuppressWarnings("unchecked")
	public List<Endereco> listAll() {
		Query query = em.createQuery("SELECT a FROM Endereco a");
		return (List<Endereco>) query.getResultList();
	}

	public Endereco findById(Long id) {
		return em.find(Endereco.class, id);
	}

	public void removerEndereco(Endereco endereco) {
		em.remove(endereco);
	}

	public void removerEndereco(Long id) {
		Query query = em.createQuery("DELETE FROM Endereco a WHERE a.id = " + id);
		query.executeUpdate();
	}

}

package br.ufms.cpcx.engweb.petshop.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufms.cpcx.engweb.petshop.model.Cidade;

@Stateless
public class CidadeDAO {
	@PersistenceContext
	private EntityManager em;

	public Cidade persist(Cidade t) {
		if (t.getId() != null) {
			return em.merge(t);
		}
		em.persist(t);
		return t;
	}

	@SuppressWarnings("unchecked")
	public List<Cidade> listAll() {
		Query query = em.createQuery("SELECT a FROM Cidade a ORDER BY a.nome");
		return (List<Cidade>) query.getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Cidade> listarCidadesPorIdEstado(Long idEstado) {
		Query query = em.createQuery("SELECT a FROM Cidade a WHERE a.estado = "+ idEstado+" ORDER BY a.nome");
		return (List<Cidade>) query.getResultList();
	}

	public Cidade findById(Long id) {
		return em.find(Cidade.class, id);
	}

	public void removerCidade(Cidade cidade) {
		em.remove(cidade);
	}

	public void removerCidade(Long id) {
		Query query = em.createQuery("DELETE FROM Cidade a WHERE a.id = " + id);
		query.executeUpdate();
	}

}

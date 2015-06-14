package br.ufms.cpcx.engweb.petshop.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufms.cpcx.engweb.petshop.model.Estado;
import br.ufms.cpcx.engweb.petshop.model.Pais;

@Stateless
public class EstadoDAO {
	@PersistenceContext
	private EntityManager em;

	public Estado persist(Estado t) {
		if (t.getId() != null) {
			return em.merge(t);
		}
		em.persist(t);
		return t;
	}

	@SuppressWarnings("unchecked")
	public List<Estado> listAll() {
		Query query = em.createQuery("SELECT a FROM Estado a ORDER BY a.nome");
		return (List<Estado>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Estado> listarEstadosPorIdPais(Long idPais) {
		Query query = em.createQuery("SELECT a FROM Estado a WHERE a.pais = "+ idPais+" ORDER BY a.nome");
		return (List<Estado>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Estado> listarEstadosPorIdPais(Pais pais) {
		Query query = em.createQuery("SELECT a FROM Estado a WHERE a.pais = "+ pais.getId()+" ORDER BY a.nome");
		return (List<Estado>) query.getResultList();
	}

	public Estado findById(Long id) {
		return em.find(Estado.class, id);
	}

	public void removerEstado(Estado estado) {
		em.remove(estado);
	}

	public void removerEstado(Long id) {
		Query query = em.createQuery("DELETE FROM Estado a WHERE a.id = " + id);
		query.executeUpdate();
	}

}

package br.ufms.cpcx.engweb.petshop.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufms.cpcx.engweb.petshop.model.Categoria;

@Stateless
public class CategoriaDAO {
	@PersistenceContext
	private EntityManager em;

	public Categoria persist(Categoria t) {
		if (t.getId() != null) {
			return em.merge(t);
		}
		em.persist(t);
		return t;
	}

	@SuppressWarnings("unchecked")
	public List<Categoria> listAll() {
		Query query = em.createQuery("SELECT a FROM Categoria a");
		return (List<Categoria>) query.getResultList();
	}

	public Categoria findById(Long id) {
		return em.find(Categoria.class, id);
	}

	public void removerCategoria(Categoria categoria) {
		em.remove(categoria);
	}

	public void removerCategoria(Long id) {
		Query query = em
				.createQuery("DELETE FROM Categoria a WHERE a.id = " + id);
		query.executeUpdate();
	}

}

package br.ufms.cpcx.engweb.petshop.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufms.cpcx.engweb.petshop.model.Produto;

@Stateless
public class ProdutoDAO {
	@PersistenceContext
	private EntityManager em;

	public Produto persist(Produto t) {
		if (t.getId() != null) {
			return em.merge(t);
		}
		em.persist(t);
		return t;
	}

	@SuppressWarnings("unchecked")
	public List<Produto> listAll() {
		Query query = em.createQuery("SELECT a FROM Produto a");
		return (List<Produto>) query.getResultList();
	}

	public Produto findById(Long id) {
		return em.find(Produto.class, id);
	}

	public void removerProduto(Produto produto) {
		em.remove(produto);
	}

	public void removerProduto(Long id) {
		Query query = em
				.createQuery("DELETE FROM Produto a WHERE a.id = " + id);
		query.executeUpdate();
	}

}

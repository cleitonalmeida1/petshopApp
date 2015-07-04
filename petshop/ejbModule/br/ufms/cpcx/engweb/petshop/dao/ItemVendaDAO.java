package br.ufms.cpcx.engweb.petshop.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufms.cpcx.engweb.petshop.model.ItemVenda;

@Stateless
public class ItemVendaDAO {
	@PersistenceContext
	private EntityManager em;

	public ItemVenda persist(ItemVenda t) {
		if (t.getId() != null) {
			return em.merge(t);
		}
		em.persist(t);
		return t;
	}

	@SuppressWarnings("unchecked")
	public List<ItemVenda> listAll() {
		Query query = em.createQuery("SELECT a FROM ItemVenda a ORDER BY a.id");
		return (List<ItemVenda>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<ItemVenda> listarItensVendaPorIdProduto(Long idProduto) {
		Query query = em.createQuery("SELECT a FROM ItemVenda a WHERE a.vendedor = " + idProduto + " ORDER BY a.id");
		return (List<ItemVenda>) query.getResultList();
	}

	public ItemVenda findById(Long id) {
		return em.find(ItemVenda.class, id);
	}

	public void removerItemVenda(ItemVenda itemVenda) {
		em.remove(itemVenda);
	}

	public void removerItemVenda(Long id) {
		Query query = em.createQuery("DELETE FROM ItemVenda a WHERE a.id = " + id);
		query.executeUpdate();
	}
}
package br.ufms.cpcx.engweb.petshop.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufms.cpcx.engweb.petshop.model.Venda;

@Stateless
public class VendaDAO {
	@PersistenceContext
	private EntityManager em;

	public Venda persist(Venda t) {
		if (t.getId() != null) {
			return em.merge(t);
		}
		em.persist(t);
		return t;
	}

	@SuppressWarnings("unchecked")
	public List<Venda> listAll() {
		Query query = em.createQuery("SELECT a FROM Venda a ORDER BY a.id");
		return (List<Venda>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Venda> listarVendasPorIdVendedor(Long idVendedor) {
		Query query = em.createQuery("SELECT a FROM Venda a WHERE a.vendedor = "+ idVendedor+" ORDER BY a.id");
		return (List<Venda>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Venda> listarVendasPorIdCliente(Long idCliente) {
		Query query = em.createQuery("SELECT a FROM Venda a WHERE a.cliente = "+ idCliente +" ORDER BY a.id");
		return (List<Venda>) query.getResultList();
	}

	public Venda findById(Long id) {
		return em.find(Venda.class, id);
	}

	public void removerVenda(Venda venda) {
		em.remove(venda);
	}

	public void removerVenda(Long id) {
		Query query = em.createQuery("DELETE FROM Venda a WHERE a.id = " + id);
		query.executeUpdate();
	}
}
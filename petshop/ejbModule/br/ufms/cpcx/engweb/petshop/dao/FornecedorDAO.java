package br.ufms.cpcx.engweb.petshop.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufms.cpcx.engweb.petshop.model.Fornecedor;

@Stateless
public class FornecedorDAO {
	@PersistenceContext
	private EntityManager em;

	public Fornecedor persist(Fornecedor t) {
		if (t.getId() != null) {
			return em.merge(t);
		}
		em.persist(t);
		return t;
	}

	@SuppressWarnings("unchecked")
	public List<Fornecedor> listAll() {
		Query query = em.createQuery("SELECT a FROM Fornecedor a");
		List<Fornecedor> lista = (List<Fornecedor>)query.getResultList();
		return lista;
	}

	public Fornecedor findById(Long id) {
		return em.find(Fornecedor.class, id);
	}

	public void removerFornecedor(Fornecedor fornecedor) {
		em.remove(fornecedor);
	}

	public void removerFornecedor(Long id) {
		Query query = em.createQuery("DELETE FROM Fornecedor a WHERE a.id = " + id);
		query.executeUpdate();
	}
}
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

	public Fornecedor persist(Fornecedor f) {
		if (f.getId() != null) {
			return em.merge(f);
		}
		em.persist(f);
		return f;
	}

	@SuppressWarnings("unchecked")
	public List<Fornecedor> listAll() {
		Query query = em.createQuery("SELECT c FROM Fornecedor c");
		return (List<Fornecedor>) query.getResultList();
	}

	public Fornecedor findById(Long id) {
		return em.find(Fornecedor.class, id);
	}

	public void removerFornecedor(Fornecedor fornecedor) {
		em.remove(fornecedor);
	}

	public void removerFornecedor(Long id) {
		// Fornecedor fornecedor = findById(id);
		// removerFornecedor(fornecedor);
		Query query = em.createQuery("DELETE FROM Fornecedor c WHERE c.id = "
				+ id);
		query.executeUpdate();
	}

}

package br.ufms.cpcx.engweb.petshop.dao;



import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufms.cpcx.engweb.petshop.model.Cliente;

@Stateless
public class ClienteDAO {
	@PersistenceContext
	private EntityManager em;

	
	public Cliente persist(Cliente t) {
		if (t.getId() != null) {
			return em.merge(t);
		}
		em.persist(t);
		return t;
	}

	
	@SuppressWarnings("unchecked")
	public List<Cliente> listAll() {
		Query query = em.createQuery("SELECT c FROM Cliente c");
		return (List<Cliente>) query.getResultList();
	}

	
	public Cliente findById(Long id) {
		return em.find(Cliente.class, id);
	}

	
	public void removerCliente(Cliente cliente) {
		em.remove(cliente);
	}

	
	public void removerCliente(Long id) {
		//Cliente cliente = findById(id);
	    //removerCliente(cliente);
		Query query = em.createQuery("DELETE FROM Cliente c WHERE c.id = "+id);
		query.executeUpdate();
	}

}


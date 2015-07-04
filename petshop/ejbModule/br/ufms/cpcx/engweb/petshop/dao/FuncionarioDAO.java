package br.ufms.cpcx.engweb.petshop.dao;



import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.ufms.cpcx.engweb.petshop.model.Funcionario;

@Stateless
public class FuncionarioDAO {
	@PersistenceContext
	private EntityManager em;

	
	public Funcionario persist(Funcionario t) {
		if (t.getId() != null) {
			return em.merge(t);
		}
		em.persist(t);
		return t;
	}

	
	@SuppressWarnings("unchecked")
	public List<Funcionario> listAll() {
		Query query = em.createQuery("SELECT c FROM Funcionario c");
		return (List<Funcionario>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> listarTodosFuncionarios() {
		Query query = em.createQuery("SELECT nome FROM Funcionario c");
		return (List<Funcionario>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> buscarFuncionariosPorIdCargo(Long idCargo) {
		Query query = em.createQuery("SELECT c FROM Funcionario c WHERE c.cargo = "+idCargo);
		return (List<Funcionario>) query.getResultList();
	}

	
	public Funcionario findById(Long id) {
		return em.find(Funcionario.class, id);
	}

	
	public void removerFuncionario(Funcionario funcionario) {
		em.remove(funcionario);
	}

	
	public void removerCliente(Long id) {
		//Cliente cliente = findById(id);
	    //removerCliente(cliente);
		Query query = em.createQuery("DELETE FROM Cliente c WHERE c.id = "+id);
		query.executeUpdate();
	}

}


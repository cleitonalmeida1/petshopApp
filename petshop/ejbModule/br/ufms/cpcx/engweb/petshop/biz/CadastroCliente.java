package br.ufms.cpcx.engweb.petshop.biz;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufms.cpcx.engweb.petshop.dao.ClienteDAO;
import br.ufms.cpcx.engweb.petshop.model.Cliente;

@Stateless
public class CadastroCliente {
	@EJB
	private ClienteDAO clienteDAO;

	public Cliente cadastrarCliente(Cliente cliente) {
		return clienteDAO.persist(cliente);
	}

	public List<Cliente> listarClientes() {
		return clienteDAO.listAll();
	}

	public List<String> listarNomesClientes() {
		return clienteDAO.listarTodosNomes();
	}
	
	public Cliente buscarClientePorId(Long id) {
		return clienteDAO.findById(id);
	}
	
	public void removerCliente(Cliente cliente) {
		clienteDAO.removerCliente(cliente.getId());
	}

}

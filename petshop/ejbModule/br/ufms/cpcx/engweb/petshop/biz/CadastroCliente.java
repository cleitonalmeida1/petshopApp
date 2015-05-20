package br.ufms.cpcx.engweb.petshop.biz;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufms.cpcx.engweb.petshop.dao.ClienteDAO;
import br.ufms.cpcx.engweb.petshop.model.Cliente;


@Stateless
public class CadastroCliente  {
	@EJB
	private ClienteDAO clienteDAO;

	
	public Cliente cadastrarCliente(Cliente cliente) {
		if(cliente.getRgIe()==null){
			System.out.println("Renda mensal não pode ser nulo");
		}
		if (cliente.getCpfCnpj() == null) {
			System.out.println("CPF Nulo");
		}
		return clienteDAO.persist(cliente);
	}

	
	public List<Cliente> listarClientes() {
		return clienteDAO.listAll();
	}

	
	public void removerCliente(Cliente cliente) {
		if (verificarVinculoClienteVenda(cliente.getId())) {
			// faz o que manda o negocio
		} else {
			clienteDAO.removerCliente(cliente.getId());
		}

	}

	
	public void removerCliente(Long id) {
		if (verificarVinculoClienteVenda(id)) {
			// faz o que manda o negocio
		} else {
			clienteDAO.removerCliente(id);
		}
	}

	private boolean verificarVinculoClienteVenda(Long id) {
		// se tiver relacionamento retorna falso
		// senão retorna verdadeiro
		return false;
	}
}


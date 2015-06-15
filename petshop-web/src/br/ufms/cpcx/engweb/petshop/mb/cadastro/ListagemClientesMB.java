package br.ufms.cpcx.engweb.petshop.mb.cadastro;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ufms.cpcx.engweb.petshop.biz.CadastroCliente;
import br.ufms.cpcx.engweb.petshop.model.Cliente;

@ManagedBean
@ViewScoped
public class ListagemClientesMB {
	private List<Cliente> clientes;
	private boolean mostrarTabela = true;
	private Long idCliente;
//
	@EJB
	private CadastroCliente cadastroCliente;
	
	@PostConstruct
	public void init() {
		clientes = cadastroCliente.listarClientes();
	}
	
	public String editar(Long idCliente){
		return "cadastroCliente?faces-redirect=true&id="+idCliente.toString();
	}

	public void remover(Cliente cliente){
		cadastroCliente.removerCliente(cliente);
		clientes = cadastroCliente.listarClientes();
		mostrarTabela = true;
		if(clientes.size()==0){
			mostrarTabela = false;
		}
	}
	
	
	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public boolean isMostrarTabela() {
		return mostrarTabela;
	}

	public void setMostrarTabela(boolean mostrarTabela) {
		this.mostrarTabela = mostrarTabela;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
}

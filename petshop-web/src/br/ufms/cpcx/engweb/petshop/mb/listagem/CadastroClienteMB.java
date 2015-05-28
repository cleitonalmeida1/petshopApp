package br.ufms.cpcx.engweb.petshop.mb.listagem;


import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufms.cpcx.engweb.petshop.biz.CadastroCliente;
import br.ufms.cpcx.engweb.petshop.model.Cliente;

@Named("cadastroClienteMB")
@ConversationScoped
public class CadastroClienteMB implements Serializable {
	//teste
	/**
	 * 
	 */
	private static final long serialVersionUID = -6707825366082704127L;
	private Cliente cliente;
	@EJB
	private CadastroCliente cadastroCliente;

	@Inject
	private Conversation conversation;

	@PostConstruct
	public void init() {
		cliente = new Cliente();
	}

	public String salvar() {
		cadastroCliente.cadastrarCliente(cliente);
		if (!conversation.isTransient()) {
			conversation.end();
		}
		return "listagemClientes?faces-redirect=true";
	}
	
	public String cancelar(){
		if (!conversation.isTransient()) {
			conversation.end();
		}
		return "listagemClientes?faces-redirect=true";
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		if (cliente.getId() != null && conversation.isTransient()) {
			conversation.begin();
		}
		this.cliente = cliente;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
}


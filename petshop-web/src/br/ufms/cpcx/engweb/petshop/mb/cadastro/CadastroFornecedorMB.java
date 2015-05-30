package br.ufms.cpcx.engweb.petshop.mb.cadastro;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufms.cpcx.engweb.petshop.biz.CadastroFornecedor;
import br.ufms.cpcx.engweb.petshop.model.Fornecedor;

@Named("cadastroFornecedorMB")
@ConversationScoped
public class CadastroFornecedorMB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4423054927476351402L;
	// teste
	/**
	 * 
	 */
	private Fornecedor fornecedor;
	@EJB
	private CadastroFornecedor cadastroFornecedor;

	@Inject
	private Conversation conversation;

	@PostConstruct
	public void init() {
		fornecedor = new Fornecedor();
	}

	public String salvar() {
		cadastroFornecedor.cadastrarFornecedor(fornecedor);
		if (!conversation.isTransient()) {
			conversation.end();
		}
		return "listagemFornecedores?faces-redirect=true";
	}

	public String cancelar() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
		return "listagemFornecedores?faces-redirect=true";
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		if (fornecedor.getId() != null && conversation.isTransient()) {
			conversation.begin();
		}
		this.fornecedor = fornecedor;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
}

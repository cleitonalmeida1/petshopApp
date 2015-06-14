package br.ufms.cpcx.engweb.petshop.mb.cadastro;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufms.cpcx.engweb.petshop.biz.CadastroTipoLogradouro;
import br.ufms.cpcx.engweb.petshop.model.TipoLogradouro;

@Named("cadastroTipoLogradouroMB")
@ConversationScoped
public class CadastroTipoLogradouroMB implements Serializable {
	
	private static final long serialVersionUID = 6490280471614152086L;

	private TipoLogradouro tipoLogradouro;

	@EJB
	private CadastroTipoLogradouro cadastroTipoLogradouro;

	@Inject
	private Conversation conversation;
	
	
	@PostConstruct
	public void init() {
		tipoLogradouro = new TipoLogradouro();
	}

	public String salvar() {
		cadastroTipoLogradouro.cadastrarTipoLogradouro(tipoLogradouro);
		if (!conversation.isTransient()) {
			conversation.end();
		}
		return "listagemTiposLogradouros?faces-redirect=true";
	}
	
	public String cancelar() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
		return "listagemTiposLogradouros?faces-redirect=true";
	}

	public TipoLogradouro getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
		if (tipoLogradouro.getId() != null && conversation.isTransient()) {
			conversation.begin();
		}
		this.tipoLogradouro = tipoLogradouro;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
}
package br.ufms.cpcx.engweb.petshop.mb.cadastro;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.ufms.cpcx.engweb.petshop.biz.CadastroPais;
import br.ufms.cpcx.engweb.petshop.model.Pais;

@Named("cadastroPaisMB")
@ConversationScoped
public class CadastroPaisMB implements Serializable {
	
	private static final long serialVersionUID = 4961950544765827234L;
	private Pais pais;

	@EJB
	private CadastroPais cadastroPais;

	@Inject
	private Conversation conversation;
	
	
	@PostConstruct
	public void init() {
		pais = new Pais();
	}

	public String salvar() {
		cadastroPais.cadastrarPais(pais);
		if (!conversation.isTransient()) {
			conversation.end();
		}
		return "listagemPaises?faces-redirect=true";
	}
	
	public String cancelar() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
		return "listagemPaises?faces-redirect=true";
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		if (pais.getId() != null && conversation.isTransient()) {
			conversation.begin();
		}
		this.pais = pais;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
}
package br.ufms.cpcx.engweb.petshop.mb.cadastro;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufms.cpcx.engweb.petshop.biz.CadastroMarca;
import br.ufms.cpcx.engweb.petshop.model.Marca;

@Named("cadastroMarcaMB")
@ConversationScoped
public class CadastroMarcaMB implements Serializable {

	private static final long serialVersionUID = 3779680976513271996L;

	private Marca marca;

	@EJB
	private CadastroMarca cadastroMarca;

	@Inject
	private Conversation conversation;
	
	
	@PostConstruct
	public void init() {
		marca = new Marca();
	}

	public String salvar() {
		cadastroMarca.cadastrarMarca(marca);
		if (!conversation.isTransient()) {
			conversation.end();
		}
		return "listagemMarcas?faces-redirect=true";
	}
	
	public String cancelar() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
		return "listagemMarcas?faces-redirect=true";
	}
	
	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		if (marca.getId() != null && conversation.isTransient()) {
			conversation.begin();
		}
		this.marca = marca;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
}
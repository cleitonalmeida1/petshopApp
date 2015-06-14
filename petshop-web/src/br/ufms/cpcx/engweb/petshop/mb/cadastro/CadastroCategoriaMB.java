package br.ufms.cpcx.engweb.petshop.mb.cadastro;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufms.cpcx.engweb.petshop.biz.CadastroCategoria;
import br.ufms.cpcx.engweb.petshop.model.Categoria;

@Named("cadastroCategoriaMB")
@ConversationScoped
public class CadastroCategoriaMB implements Serializable {

	private static final long serialVersionUID = -2282757029222826139L;

	private Categoria categoria;

	@EJB
	private CadastroCategoria cadastroCategoria;

	@Inject
	private Conversation conversation;

	@PostConstruct
	public void init() {
		categoria = new Categoria();
	}

	public String salvar() {
		cadastroCategoria.cadastrarCategoria(categoria);
		if (!conversation.isTransient()) {
			conversation.end();
		}
		return "listagemCategorias?faces-redirect=true";
	}

	public String cancelar() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
		return "listagemCategorias?faces-redirect=true";
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		if (categoria.getId() != null && conversation.isTransient()) {
			conversation.begin();
		}
		this.categoria = categoria;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
}
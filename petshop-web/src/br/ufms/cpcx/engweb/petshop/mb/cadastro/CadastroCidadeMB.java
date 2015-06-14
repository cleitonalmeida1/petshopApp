package br.ufms.cpcx.engweb.petshop.mb.cadastro;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.ufms.cpcx.engweb.petshop.biz.CadastroCidade;
import br.ufms.cpcx.engweb.petshop.biz.CadastroEstado;
import br.ufms.cpcx.engweb.petshop.model.Cidade;
import br.ufms.cpcx.engweb.petshop.model.Estado;

@Named("cadastroCidadeMB")
@ConversationScoped
public class CadastroCidadeMB implements Serializable {
	
	private static final long serialVersionUID = -8594010348706428695L;
	private Cidade cidade;
	private Long idEstado;
	private List<Estado> estados;
	
	@EJB
	private CadastroCidade cadastroCidade;

	@EJB
	private CadastroEstado cadastroEstado;

	@Inject
	private Conversation conversation;
	
	

	@PostConstruct
	public void init() {
		// Carrega uma lista de Estados para serem apresentadas no OneSelectMenu (combobox)
		estados = cadastroEstado.listarEstados();
		// Deixa nenhum Estado selecionado
		idEstado = null;
		
		cidade = new Cidade();
	}

	public String salvar() {
		Estado estado = procurarEstadoNaLista(idEstado);
		cidade.setEstado(estado);
		cadastroCidade.cadastrarCidade(cidade);
		if (!conversation.isTransient()) {
			conversation.end();
		}
		return "listagemCidades?faces-redirect=true";
	}
	
	public Estado procurarEstadoNaLista(Long id) {
		for (Estado e : estados) {
			if (e.getId().compareTo(id) == 0) {
				return e;
			}
		}
		return null;
	}

	public String cancelar() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
		return "listagemCidades?faces-redirect=true";
	}
	
	

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		if (cidade.getId() != null && conversation.isTransient()) {
			conversation.begin();
		}
		this.cidade = cidade;
	}

	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}	

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
}

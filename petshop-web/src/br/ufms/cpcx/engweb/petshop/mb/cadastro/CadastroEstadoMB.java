package br.ufms.cpcx.engweb.petshop.mb.cadastro;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.ufms.cpcx.engweb.petshop.biz.CadastroEstado;
import br.ufms.cpcx.engweb.petshop.biz.CadastroPais;
import br.ufms.cpcx.engweb.petshop.model.Estado;
import br.ufms.cpcx.engweb.petshop.model.Pais;

@Named("cadastroEstadoMB")
@ConversationScoped
public class CadastroEstadoMB implements Serializable {
	
	private static final long serialVersionUID = 6273969785859361886L;
	private Estado estado;
	private Long idPais;
	private List<Pais> paises;

	@EJB
	private CadastroEstado cadastroEstado;
	
	@EJB
	private CadastroPais cadastroPais;

	@Inject
	private Conversation conversation;
	
	
	@PostConstruct
	public void init() {
		// Carrega uma lista de Países para serem apresentados no OneSelectMenu (combobox)
		paises = cadastroPais.listarPaises();
		// Deixa nenhum País selecionado
		idPais = null;
		
		estado = new Estado();
	}

	public String salvar() {
		Pais pais = procurarPaisNaLista(idPais);
		estado.setPais(pais);
		cadastroEstado.cadastrarEstado(estado);
		if (!conversation.isTransient()) {
			conversation.end();
		}
		return "listagemEstados?faces-redirect=true";
	}
	
	public Pais procurarPaisNaLista(Long id) {
		for (Pais p : paises) {
			if (p.getId().compareTo(id) == 0) {
				return p;
			}
		}
		return null;
	}

	public String cancelar() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
		return "listagemEstados?faces-redirect=true";
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		if (estado.getId() != null && conversation.isTransient()) {
			conversation.begin();
		}
		this.estado = estado;
	}

	public Long getIdPais() {
		return idPais;
	}

	public void setIdPais(Long idPais) {
		this.idPais = idPais;
	}

	public List<Pais> getPaises() {
		return paises;
	}

	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
}

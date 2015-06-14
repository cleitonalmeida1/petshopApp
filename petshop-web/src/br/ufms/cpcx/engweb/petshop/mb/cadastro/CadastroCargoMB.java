package br.ufms.cpcx.engweb.petshop.mb.cadastro;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.ufms.cpcx.engweb.petshop.biz.CadastroCargo;
import br.ufms.cpcx.engweb.petshop.model.Cargo;

@Named("cadastroCargoMB")
@ConversationScoped
public class CadastroCargoMB implements Serializable {

	private static final long serialVersionUID = -3954349164232233828L;

	private Cargo cargo;

	

	@EJB
	private CadastroCargo cadastroCargo;

	@Inject
	private Conversation conversation;
	
	
	@PostConstruct
	public void init() {
		cargo = new Cargo();
		
	}

	public String salvar() {
		cadastroCargo.cadastrarCargo(cargo);
		if (!conversation.isTransient()) {
			conversation.end();
		}
		return "listagemCargos?faces-redirect=true";
	}
	
	public String cancelar() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
		return "listagemCargos?faces-redirect=true";
	}
	
	public void salvarCodigo() {
		System.out.println("ENTROU");
		RequestContext.getCurrentInstance().execute("alert('teste');");
	}
	

	public void setCargo(Cargo cargo) {
		if (cargo.getId() != null && conversation.isTransient()) {
			conversation.begin();
		}
		this.cargo = cargo;
	}
	
	public Cargo getCargo() {
		return cargo;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
}
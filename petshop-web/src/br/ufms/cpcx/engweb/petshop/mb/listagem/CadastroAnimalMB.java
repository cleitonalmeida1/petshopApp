package br.ufms.cpcx.engweb.petshop.mb.listagem;


import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.ufms.cpcx.engweb.petshop.biz.CadastroAnimal;
import br.ufms.cpcx.engweb.petshop.biz.CadastroFoto;
import br.ufms.cpcx.engweb.petshop.model.Animal;
import br.ufms.cpcx.engweb.petshop.model.Foto;

@Named("cadastroAnimalMB")
@ConversationScoped
public class CadastroAnimalMB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6707825366082704127L;
	private Animal animal;
	@EJB
	private CadastroAnimal cadastroAnimal;
	
	@EJB
	private CadastroFoto cadastroFoto;

	@Inject
	private Conversation conversation;

	@PostConstruct
	public void init() {
		animal = new Animal();
	}

	public String salvar(UploadedFile arquivo) {
		
		if(arquivo!=null){
			Foto foto = new Foto();
			foto.setContent(arquivo.getContents());
			foto.setNome(arquivo.getFileName());
			foto.setTipo(arquivo.getContentType());
			foto = cadastroFoto.cadastrarFoto(foto);
			animal.setFoto(foto);
		}
		
		cadastroAnimal.cadastrarAnimal(animal);
		if (!conversation.isTransient()) {
			conversation.end();
		}
		return "listagemAnimais?faces-redirect=true";
	}
	
	public String cancelar(){
		if (!conversation.isTransient()) {
			conversation.end();
		}
		return "listagemAnimais?faces-redirect=true";
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		if (animal.getId() != null && conversation.isTransient()) {
			conversation.begin();
		}
		this.animal = animal;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
}


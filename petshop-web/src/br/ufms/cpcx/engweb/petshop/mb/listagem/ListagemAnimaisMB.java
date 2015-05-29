package br.ufms.cpcx.engweb.petshop.mb.listagem;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ufms.cpcx.engweb.petshop.biz.CadastroAnimal;
import br.ufms.cpcx.engweb.petshop.model.Animal;

@ManagedBean
@ViewScoped
public class ListagemAnimaisMB {
	private List<Animal> animais;
	private boolean mostrarTabela = true;
	private Long idAnimal;
//
	@EJB
	private CadastroAnimal cadastroAnimal;
	
	@PostConstruct
	public void init() {
		animais = cadastroAnimal.listarAnimais();
	}
	
	public String editar(){
		return "cadastroAnimal?faces-redirect=true";
	}

	public void remover(Animal animal){
		cadastroAnimal.removerAnimal(animal);
		animais = cadastroAnimal.listarAnimais();
		mostrarTabela = true;
		if(animais.size()==0){
			mostrarTabela = false;
		}
	}
	
	
	public List<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}

	public boolean isMostrarTabela() {
		return mostrarTabela;
	}

	public void setMostrarTabela(boolean mostrarTabela) {
		this.mostrarTabela = mostrarTabela;
	}

	public Long getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(Long idAnimal) {
		this.idAnimal = idAnimal;
	}
}

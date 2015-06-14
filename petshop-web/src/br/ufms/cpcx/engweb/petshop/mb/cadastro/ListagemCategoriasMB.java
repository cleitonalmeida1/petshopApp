package br.ufms.cpcx.engweb.petshop.mb.cadastro;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import br.ufms.cpcx.engweb.petshop.biz.CadastroCategoria;
import br.ufms.cpcx.engweb.petshop.model.Categoria;

@ManagedBean
@ViewScoped
@Named("listagemCategoriasMB")
public class ListagemCategoriasMB {
	private List<Categoria> categorias;
	private boolean mostrarTabela = true;
	private Long idCategoria;
	

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public boolean isMostrarTabela() {
		return mostrarTabela;
	}

	public void setMostrarTabela(boolean mostrarTabela) {
		this.mostrarTabela = mostrarTabela;
	}

	@EJB
	private CadastroCategoria cadastroCategoria;

	@PostConstruct
	public void init() {
		categorias = cadastroCategoria.listarCategorias();
	}

	public String editar() {
		return "cadastroCategoria?faces-redirect=true";
	}

	public void remover(Categoria categoria) {
		cadastroCategoria.removerCategoria(categoria);
		categorias = cadastroCategoria.listarCategorias();
		mostrarTabela = true;
		if (categorias.size() == 0) {
			mostrarTabela = false;
		}
	}
}

package br.ufms.cpcx.engweb.petshop.mb.cadastro;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import br.ufms.cpcx.engweb.petshop.biz.CadastroCidade;
import br.ufms.cpcx.engweb.petshop.model.Cidade;

@ManagedBean
@ViewScoped
@Named("ListagemCidadesMB")
public class ListagemCidadesMB {
	private List<Cidade> cidades;
	private boolean mostrarTabela = true;
	private Long idCidade;
	
	@EJB
	private CadastroCidade cadastroCidade;

	@PostConstruct
	public void init() {
		cidades = cadastroCidade.listarCidades();
	}

	public String editar() {
		return "cadastroCidade?faces-redirect=true";
	}

	public void remover(Cidade cidade) {
		cadastroCidade.removerCidade(cidade);
		cidades = cadastroCidade.listarCidades();
		mostrarTabela = true;
		if (cidades.size() == 0) {
			mostrarTabela = false;
		}
	}
	
	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public boolean isMostrarTabela() {
		return mostrarTabela;
	}

	public void setMostrarTabela(boolean mostrarTabela) {
		this.mostrarTabela = mostrarTabela;
	}

	public Long getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Long idCidade) {
		this.idCidade = idCidade;
	}
}
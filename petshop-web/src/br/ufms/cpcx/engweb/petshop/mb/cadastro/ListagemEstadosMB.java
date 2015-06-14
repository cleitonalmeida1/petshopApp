package br.ufms.cpcx.engweb.petshop.mb.cadastro;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import br.ufms.cpcx.engweb.petshop.biz.CadastroEstado;
import br.ufms.cpcx.engweb.petshop.model.Estado;

@ManagedBean
@ViewScoped
@Named("ListagemEstadosMB")
public class ListagemEstadosMB {
	private List<Estado> estados;
	private boolean mostrarTabela = true;
	private Long idEstado;
	
	@EJB
	private CadastroEstado cadastroEstado;

	@PostConstruct
	public void init() {
		estados = cadastroEstado.listarEstados();
	}

	public String editar() {
		return "cadastroEstado?faces-redirect=true";
	}

	public void remover(Estado estado) {
		cadastroEstado.removerEstado(estado);
		estados = cadastroEstado.listarEstados();
		mostrarTabela = true;
		if (estados.size() == 0) {
			mostrarTabela = false;
		}
	}
	
	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public boolean isMostrarTabela() {
		return mostrarTabela;
	}

	public void setMostrarTabela(boolean mostrarTabela) {
		this.mostrarTabela = mostrarTabela;
	}

	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}
}
package br.ufms.cpcx.engweb.petshop.mb.cadastro;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import br.ufms.cpcx.engweb.petshop.biz.CadastroCargo;
import br.ufms.cpcx.engweb.petshop.model.Cargo;

@ManagedBean
@ViewScoped
@Named("listagemCargosMB")
public class ListagemCargosMB {
	private List<Cargo> cargos;
	private boolean mostrarTabela = true;
	private Long idCargo;
	
	

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	public Long getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(Long idCargo) {
		this.idCargo = idCargo;
	}

	public boolean isMostrarTabela() {
		return mostrarTabela;
	}

	public void setMostrarTabela(boolean mostrarTabela) {
		this.mostrarTabela = mostrarTabela;
	}

	@EJB
	private CadastroCargo cadastroCargo;

	@PostConstruct
	public void init() {
		cargos = cadastroCargo.listarCargos();
	}

	public String editar() {
		return "cadastroCargo?faces-redirect=true";
	}

	public void remover(Cargo cargo) {
		cadastroCargo.removerCargo(cargo);
		cargos = cadastroCargo.listarCargos();
		mostrarTabela = true;
		if (cargos.size() == 0) {
			mostrarTabela = false;
		}
	}
}

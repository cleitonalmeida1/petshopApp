package br.ufms.cpcx.engweb.petshop.mb.cadastro;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import br.ufms.cpcx.engweb.petshop.biz.CadastroTipoLogradouro;
import br.ufms.cpcx.engweb.petshop.model.TipoLogradouro;

@ManagedBean
@ViewScoped
@Named("listagemTiposLogradourosMB")
public class ListagemTiposLogradourosMB {
	private List<TipoLogradouro> tiposDeLogradouros;
	private boolean mostrarTabela = true;
	private Long idTipoLogradouro;

	public List<TipoLogradouro> getTiposDeLogradouros() {
		return tiposDeLogradouros;
	}

	public void setTiposDeLogradouros(List<TipoLogradouro> tiposDeLogradouros) {
		this.tiposDeLogradouros = tiposDeLogradouros;
	}

	public boolean isMostrarTabela() {
		return mostrarTabela;
	}

	public void setMostrarTabela(boolean mostrarTabela) {
		this.mostrarTabela = mostrarTabela;
	}

	public Long getIdTipoLogradouro() {
		return idTipoLogradouro;
	}

	public void setIdTipoLogradouro(Long idTipoLogradouro) {
		this.idTipoLogradouro = idTipoLogradouro;
	}

	@EJB
	private CadastroTipoLogradouro cadastroTipoLogradouro;

	@PostConstruct
	public void init() {
		tiposDeLogradouros = cadastroTipoLogradouro.listarTiposDeLogradouros();
	}

	public String editar() {
		return "cadastroTipoLogradouro?faces-redirect=true";
	}

	public void remover(TipoLogradouro tipoLogradouro) {
		cadastroTipoLogradouro.removerTipoLogradouro(tipoLogradouro);
		tiposDeLogradouros = cadastroTipoLogradouro.listarTiposDeLogradouros();
		mostrarTabela = true;
		if (tiposDeLogradouros.size() == 0) {
			mostrarTabela = false;
		}
	}
}

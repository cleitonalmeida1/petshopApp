package br.ufms.cpcx.engweb.petshop.mb.cadastro;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import br.ufms.cpcx.engweb.petshop.biz.CadastroPais;
import br.ufms.cpcx.engweb.petshop.model.Pais;

@ManagedBean
@ViewScoped
@Named("listagemPaisesMB")
public class ListagemPaisesMB {
	private List<Pais> paises;
	private boolean mostrarTabela = true;
	private Long idPais;
	
	public List<Pais> getPaises() {
		return paises;
	}

	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}

	public boolean isMostrarTabela() {
		return mostrarTabela;
	}

	public void setMostrarTabela(boolean mostrarTabela) {
		this.mostrarTabela = mostrarTabela;
	}

	public Long getIdPais() {
		return idPais;
	}

	public void setIdPais(Long idPais) {
		this.idPais = idPais;
	}

	@EJB
	private CadastroPais cadastroPais;

	@PostConstruct
	public void init() {
		paises = cadastroPais.listarPaises();
	}

	public String editar() {
		return "cadastroPais?faces-redirect=true";
	}

	public void remover(Pais pais) {
		cadastroPais.removerPais(pais);
		paises = cadastroPais.listarPaises();
		mostrarTabela = true;
		if (paises.size() == 0) {
			mostrarTabela = false;
		}
	}
}

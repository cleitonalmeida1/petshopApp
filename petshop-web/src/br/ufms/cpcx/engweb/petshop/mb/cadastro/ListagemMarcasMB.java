package br.ufms.cpcx.engweb.petshop.mb.cadastro;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import br.ufms.cpcx.engweb.petshop.biz.CadastroMarca;
import br.ufms.cpcx.engweb.petshop.model.Marca;

@ManagedBean
@ViewScoped
@Named("listagemMarcasMB")
public class ListagemMarcasMB {
	private List<Marca> marcas;
	private boolean mostrarTabela = true;
	private Long idMarca;
	
	public List<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

	public Long getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(Long idMarca) {
		this.idMarca = idMarca;
	}

	public boolean isMostrarTabela() {
		return mostrarTabela;
	}

	public void setMostrarTabela(boolean mostrarTabela) {
		this.mostrarTabela = mostrarTabela;
	}

	@EJB
	private CadastroMarca cadastroMarca;

	@PostConstruct
	public void init() {
		marcas = cadastroMarca.listarMarcas();
	}

	public String editar() {
		return "cadastroMarcas?faces-redirect=true";
	}

	public void remover(Marca marca) {
		cadastroMarca.removerMarca(marca);
		marcas = cadastroMarca.listarMarcas();
		mostrarTabela = true;
		if (marcas.size() == 0) {
			mostrarTabela = false;
		}
	}
}

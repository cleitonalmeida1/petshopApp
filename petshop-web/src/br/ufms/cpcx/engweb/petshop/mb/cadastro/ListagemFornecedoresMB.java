package br.ufms.cpcx.engweb.petshop.mb.cadastro;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ufms.cpcx.engweb.petshop.biz.CadastroFornecedor;
import br.ufms.cpcx.engweb.petshop.model.Fornecedor;

@ManagedBean
@ViewScoped
public class ListagemFornecedoresMB {
	private List<Fornecedor> fornecedores;
	private boolean mostrarTabela = true;
	private Long idFornecedor;
	//
	@EJB
	private CadastroFornecedor cadastroFornecedor;

	@PostConstruct
	public void init() {
		fornecedores = cadastroFornecedor.listarFornecedores();
	}

	public String editar() {
		return "cadastroFornecedor?faces-redirect=true";
	}

	public void remover(Fornecedor fornecedor) {
		cadastroFornecedor.removerFornecedor(fornecedor);
		fornecedores = cadastroFornecedor.listarFornecedores();
		mostrarTabela = true;
		if (fornecedores.size() == 0) {
			mostrarTabela = false;
		}
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public boolean isMostrarTabela() {
		return mostrarTabela;
	}

	public void setMostrarTabela(boolean mostrarTabela) {
		this.mostrarTabela = mostrarTabela;
	}

	public Long getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Long idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
}

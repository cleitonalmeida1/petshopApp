package br.ufms.cpcx.engweb.petshop.mb.cadastro;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ufms.cpcx.engweb.petshop.biz.CadastroProduto;
import br.ufms.cpcx.engweb.petshop.model.Produto;

@ManagedBean(name = "listagemProdutosMB")
@ViewScoped
public class ListagemProdutosMB {
	private List<Produto> produtos;
	private boolean mostrarTabela = true;
	private Long idProduto;
	//
	@EJB
	private CadastroProduto cadastroProduto;

	@PostConstruct
	public void init() {
		produtos = cadastroProduto.listarProdutos();
	}

	public String editar(Long idProduto) {
		return "cadastroProduto?faces-redirect=true&id="+idProduto.toString();
	}

	public void remover(Produto produto) {
		cadastroProduto.removerProduto(produto);
		produtos = cadastroProduto.listarProdutos();
		mostrarTabela = true;
		if (produtos.size() == 0) {
			mostrarTabela = false;
		}
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public boolean isMostrarTabela() {
		return mostrarTabela;
	}

	public void setMostrarTabela(boolean mostrarTabela) {
		this.mostrarTabela = mostrarTabela;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
}

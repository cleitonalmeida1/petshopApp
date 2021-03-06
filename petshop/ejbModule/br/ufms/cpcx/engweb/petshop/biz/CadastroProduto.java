package br.ufms.cpcx.engweb.petshop.biz;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufms.cpcx.engweb.petshop.dao.ProdutoDAO;
import br.ufms.cpcx.engweb.petshop.model.Produto;

@Stateless
public class CadastroProduto {
	@EJB
	private ProdutoDAO produtoDAO;

	public Produto cadastrarProduto(Produto produto) {
		return produtoDAO.persist(produto);
	}

	public List<Produto> listarProdutos() {
		return produtoDAO.listAll();
	}
	
	public Produto buscarProdutoPorId(Long idProduto) {
		return produtoDAO.findById(idProduto);
	}
	
	public void diminuirProdutoDoEstoque(Long  idProduto, Integer qtde) {
		produtoDAO.diminuirProdutoDoEstoque(idProduto, qtde);
	}

	public void removerProduto(Produto produto) {
		produtoDAO.removerProduto(produto.getId());
	}

}

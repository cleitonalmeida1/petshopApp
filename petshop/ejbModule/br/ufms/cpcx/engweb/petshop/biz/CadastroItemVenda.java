package br.ufms.cpcx.engweb.petshop.biz;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.ufms.cpcx.engweb.petshop.dao.ItemVendaDAO;
import br.ufms.cpcx.engweb.petshop.model.ItemVenda;

@Stateless
public class CadastroItemVenda {
	@EJB
	private ItemVendaDAO itemVendaDAO;

	public ItemVenda cadastrarItemVenda(ItemVenda itemVenda) {
		return itemVendaDAO.persist(itemVenda);
	}

	public List<ItemVenda> listarItensVendas() {
		return itemVendaDAO.listAll();
	}

	public List<ItemVenda> listarVendasPorIdProduto(Long idProduto) {
		return itemVendaDAO.listarItensVendaPorIdProduto(idProduto);
	}

	public ItemVenda buscarItemVendaPorId(Long idItemVenda) {
		return itemVendaDAO.findById(idItemVenda);
	}

	public void removerItemVenda(ItemVenda itemVenda) {
		itemVendaDAO.removerItemVenda(itemVenda);
	}

	public void removerItemVenda(Long idItemVenda) {
		itemVendaDAO.removerItemVenda(idItemVenda);
	}
}
package br.ufms.cpcx.engweb.petshop.biz;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufms.cpcx.engweb.petshop.dao.VendaDAO;
import br.ufms.cpcx.engweb.petshop.model.Venda;

@Stateless
public class CadastroVenda {
	@EJB
	private VendaDAO vendaDAO;

	public Venda cadastrarVenda(Venda venda) {
		return vendaDAO.persist(venda);
	}

	public List<Venda> listarVendas() {
		return vendaDAO.listAll();
	}
	
	public List<Venda> listarVendasPorIdVendedor(Long idVendedor) {
		return vendaDAO.listarVendasPorIdVendedor(idVendedor);
	}
	
	public List<Venda> listarVendasPorIdCliente(Long idCliente) {
		return vendaDAO.listarVendasPorIdCliente(idCliente);
	}
	
	public Venda buscarVendaPorId(Long idVenda) {
		return vendaDAO.findById(idVenda);
	}

	public void removerVenda(Venda venda) {
		vendaDAO.removerVenda(venda);
	}

}

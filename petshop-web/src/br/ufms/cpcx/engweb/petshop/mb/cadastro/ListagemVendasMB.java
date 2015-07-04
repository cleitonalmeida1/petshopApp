package br.ufms.cpcx.engweb.petshop.mb.cadastro;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.ufms.cpcx.engweb.petshop.biz.CadastroVenda;
import br.ufms.cpcx.engweb.petshop.model.Venda;

@ManagedBean(name = "listagemVendasMB")
@ViewScoped
public class ListagemVendasMB {
	private List<Venda> vendas;
	private boolean mostrarTabela = true;
	private Long idVenda;

	@EJB
	private CadastroVenda cadastroVenda;

	@PostConstruct
	public void init() {
		setVendas(cadastroVenda.listarVendas());
	}

	public String editar(Long idVenda) {
		return "cadastroVenda?faces-redirect=true&id=" + idVenda.toString();
	}

	public void remover(Venda venda) {
		//venda.setItensVenda(new ArrayList<ItemVenda>());
		//venda = cadastroVenda.cadastrarVenda(venda);
		//venda = cadastroVenda.buscarVendaPorId(venda.getId());
		cadastroVenda.removerVenda(venda);
		vendas = cadastroVenda.listarVendas();
		mostrarTabela = true;
		if (vendas.size() == 0) {
			mostrarTabela = false;
		}
	}

	public boolean isMostrarTabela() {
		return mostrarTabela;
	}

	public void setMostrarTabela(boolean mostrarTabela) {
		this.mostrarTabela = mostrarTabela;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public Long getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(Long idVenda) {
		this.idVenda = idVenda;
	}
}
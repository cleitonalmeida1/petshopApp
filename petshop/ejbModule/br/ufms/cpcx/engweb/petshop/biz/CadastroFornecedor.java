package br.ufms.cpcx.engweb.petshop.biz;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufms.cpcx.engweb.petshop.dao.FornecedorDAO;
import br.ufms.cpcx.engweb.petshop.model.Fornecedor;


@Stateless
public class CadastroFornecedor {
	@EJB
	private FornecedorDAO fornecedorDAO;

	public Fornecedor cadastrarFornecedor(Fornecedor fornecedor) {
		return fornecedorDAO.persist(fornecedor);
	}

	public List<Fornecedor> listarFornecedores() {
		return fornecedorDAO.listAll();
	}
	
	public Fornecedor buscarFornecedorPorId(Long id) {
		return fornecedorDAO.findById(id);
	}

	public void removerFornecedor(Fornecedor fornecedor) {
		fornecedorDAO.removerFornecedor(fornecedor.getId());
	}

}

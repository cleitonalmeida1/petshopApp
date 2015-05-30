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
		if (fornecedor.getNome() == null) {
			System.out.println("Razão Social não pode ser nulo");
		}
		if (fornecedor.getCnpj() == null) {
			System.out.println("CNPJ não pode ser nulo");
		}
		if (fornecedor.getIe() == null) {
			System.out.println("Inscrição Estadual não pode ser nulo");
		}
		if (fornecedor.getTelefone() == null) {
			System.out.println("Telefone não pode ser nulo");
		}
		if (fornecedor.getEmail() == null) {
			System.out.println("Email não pode ser nulo");
		}
		if (fornecedor.getEndereco() == null) {
			System.out.println("Endereço não pode ser nulo");
		}
		return fornecedorDAO.persist(fornecedor);
	}

	public List<Fornecedor> listarFornecedores() {
		return fornecedorDAO.listAll();
	}

	public void removerFornecedor(Fornecedor fornecedor) {
		if (verificarVinculoFornecedorVenda(fornecedor.getId())) {
			// faz o que manda o negocio
		} else {
			fornecedorDAO.removerFornecedor(fornecedor.getId());
		}

	}

	public void removerFornecedor(Long id) {
		if (verificarVinculoFornecedorVenda(id)) {
			// faz o que manda o negocio
		} else {
			fornecedorDAO.removerFornecedor(id);
		}
	}

	private boolean verificarVinculoFornecedorVenda(Long id) {
		// se tiver relacionamento retorna falso
		// senão retorna verdadeiro
		return false;
	}
}

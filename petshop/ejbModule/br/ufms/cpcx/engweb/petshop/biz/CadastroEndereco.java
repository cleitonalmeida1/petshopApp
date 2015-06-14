package br.ufms.cpcx.engweb.petshop.biz;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufms.cpcx.engweb.petshop.dao.EnderecoDAO;
import br.ufms.cpcx.engweb.petshop.model.Endereco;

@Stateless
public class CadastroEndereco {
	@EJB
	private EnderecoDAO enderecoDAO;

	public Endereco cadastrarEndereco(Endereco endereco) {
		return enderecoDAO.persist(endereco);
	}

	public List<Endereco> listarEnderecos() {
		return enderecoDAO.listAll();
	}
	
	public Endereco buscarEnderecoPorId(Long id) {
		return enderecoDAO.findById(id);
	}

	public void removerEndereco(Endereco endereco) {
		enderecoDAO.removerEndereco(endereco.getId());
	}

}

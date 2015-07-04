package br.ufms.cpcx.engweb.petshop.biz;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufms.cpcx.engweb.petshop.dao.FuncionarioDAO;
import br.ufms.cpcx.engweb.petshop.model.Funcionario;

@Stateless
public class CadastroFuncionario {
	@EJB
	private FuncionarioDAO funcionarioDAO;

	public Funcionario cadastrarFuncionario(Funcionario funcionario) {
		return funcionarioDAO.persist(funcionario);
	}

	public List<Funcionario> listarFuncionarios() {
		return funcionarioDAO.listAll();
	}

	public Funcionario buscarFuncionarioPorId(Long id) {
		return funcionarioDAO.findById(id);
	}
	
	public List<Funcionario> buscarFuncionariosPorIdCargo(Long idCargo) {
		return funcionarioDAO.buscarFuncionariosPorIdCargo(idCargo);
	}
	

	public void removerFuncionario(Funcionario funcionario) {
		funcionarioDAO.removerFuncionario(funcionario);
	}

}

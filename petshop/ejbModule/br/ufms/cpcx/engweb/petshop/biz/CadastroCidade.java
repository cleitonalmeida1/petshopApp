package br.ufms.cpcx.engweb.petshop.biz;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufms.cpcx.engweb.petshop.dao.CidadeDAO;
import br.ufms.cpcx.engweb.petshop.model.Cidade;

@Stateless
public class CadastroCidade {
	@EJB
	private CidadeDAO cidadeDAO;

	public Cidade cadastrarCidade(Cidade cidade) {
		return cidadeDAO.persist(cidade);
	}

	public List<Cidade> listarCidades() {
		return cidadeDAO.listAll();
	}
	
	public List<Cidade> listarCidadesPorIdEstado(Long idEstado) {
		return cidadeDAO.listarCidadesPorIdEstado(idEstado);
	}
	
	public Cidade buscarCidadePorId(Long id) {
		return cidadeDAO.findById(id);
	}

	public void removerCidade(Cidade cidade) {
		cidadeDAO.removerCidade(cidade.getId());
	}

}

package br.ufms.cpcx.engweb.petshop.biz;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufms.cpcx.engweb.petshop.dao.TipoLogradouroDAO;
import br.ufms.cpcx.engweb.petshop.model.TipoLogradouro;

@Stateless
public class CadastroTipoLogradouro {
	@EJB
	private TipoLogradouroDAO tipoLogradouroDAO;

	public TipoLogradouro cadastrarTipoLogradouro(TipoLogradouro tipoLogradouro) {
		return tipoLogradouroDAO.persist(tipoLogradouro);
	}

	public List<TipoLogradouro> listarTiposDeLogradouros() {
		return tipoLogradouroDAO.listAll();
	}
	
	public TipoLogradouro buscarTipoLogradouroPorId(Long id) {
		return tipoLogradouroDAO.findById(id);
	}

	public void removerTipoLogradouro(TipoLogradouro tipoLogradouro) {
		tipoLogradouroDAO.removerTipoLogradouro(tipoLogradouro.getId());
	}

}

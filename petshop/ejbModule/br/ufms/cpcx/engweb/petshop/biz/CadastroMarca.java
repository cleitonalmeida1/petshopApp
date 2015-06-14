package br.ufms.cpcx.engweb.petshop.biz;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufms.cpcx.engweb.petshop.dao.MarcaDAO;
import br.ufms.cpcx.engweb.petshop.model.Marca;

@Stateless
public class CadastroMarca {
	@EJB
	private MarcaDAO marcaDAO;

	public Marca cadastrarMarca(Marca marca) {
		return marcaDAO.persist(marca);
	}

	public List<Marca> listarMarcas() {
		return marcaDAO.listAll();
	}
	
	public Marca buscarMarcaPorId(Long id) {
		return marcaDAO.findById(id);
	}

	public void removerMarca(Marca marca) {
		marcaDAO.removerMarca(marca.getId());
	}
}
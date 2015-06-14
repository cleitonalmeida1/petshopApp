package br.ufms.cpcx.engweb.petshop.biz;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufms.cpcx.engweb.petshop.dao.PaisDAO;
import br.ufms.cpcx.engweb.petshop.model.Pais;

@Stateless
public class CadastroPais {
	@EJB
	private PaisDAO paisDAO;

	public Pais cadastrarPais(Pais pais) {
		return paisDAO.persist(pais);
	}

	public List<Pais> listarPaises() {
		return paisDAO.listAll();
	}
	
	public Pais buscarPaisPorId(Long idPais) {
		return paisDAO.findById(idPais);
	}

	public void removerPais(Pais pais) {
		paisDAO.removerPais(pais.getId());
	}

}

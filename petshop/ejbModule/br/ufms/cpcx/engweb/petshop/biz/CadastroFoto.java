package br.ufms.cpcx.engweb.petshop.biz;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufms.cpcx.engweb.petshop.dao.FotoDAO;
import br.ufms.cpcx.engweb.petshop.model.Foto;



@Stateless
public class CadastroFoto{
	@EJB
	private FotoDAO fotoDAO;

	public Foto cadastrarFoto(Foto foto) {
		return fotoDAO.persist(foto);
	}

	public List<Foto> listarFotos() {
		return fotoDAO.listAll();
	}

	public Foto buscarFotoPorId(Long id) {
		return fotoDAO.findById(id);
	}
}

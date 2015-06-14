package br.ufms.cpcx.engweb.petshop.biz;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufms.cpcx.engweb.petshop.dao.TelefoneDAO;
import br.ufms.cpcx.engweb.petshop.model.Telefone;

@Stateless
public class CadastroTelefone {
	@EJB
	private TelefoneDAO telefoneDAO;

	public Telefone cadastrarTelefone(Telefone telefone) {
		return telefoneDAO.persist(telefone);
	}

	public List<Telefone> listarTelefones() {
		return telefoneDAO.listAll();
	}
	
	public Telefone buscarTelefonePorId(Long id) {
		return telefoneDAO.findById(id);
	}

	public void removerTelefone(Telefone telefone) {
		telefoneDAO.removerTelefone(telefone.getId());
	}
}
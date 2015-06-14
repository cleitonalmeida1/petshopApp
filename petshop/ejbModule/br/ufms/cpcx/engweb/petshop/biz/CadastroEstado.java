package br.ufms.cpcx.engweb.petshop.biz;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufms.cpcx.engweb.petshop.dao.EstadoDAO;
import br.ufms.cpcx.engweb.petshop.model.Estado;
import br.ufms.cpcx.engweb.petshop.model.Pais;

@Stateless
public class CadastroEstado {
	@EJB
	private EstadoDAO estadoDAO;

	public Estado cadastrarEstado(Estado estado) {
		return estadoDAO.persist(estado);
	}

	public List<Estado> listarEstados() {
		return estadoDAO.listAll();
	}
	
	public List<Estado> listarEstadosPorIdPais(Long idPais) {
		return estadoDAO.listarEstadosPorIdPais(idPais);
	}
	
	public List<Estado> listarEstadosPorIdPais(Pais pais) {
		return estadoDAO.listarEstadosPorIdPais(pais);
	}
	
	public Estado buscarEstadoPorId(Long idEstado) {
		return estadoDAO.findById(idEstado);
	}

	public void removerEstado(Estado estado) {
		estadoDAO.removerEstado(estado.getId());
	}

}

package br.ufms.cpcx.engweb.petshop.biz;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufms.cpcx.engweb.petshop.dao.CargoDAO;
import br.ufms.cpcx.engweb.petshop.model.Cargo;

@Stateless
public class CadastroCargo {
	@EJB
	private CargoDAO cargoDAO;

	public Cargo cadastrarCargo(Cargo cargo) {
		return cargoDAO.persist(cargo);
	}

	public List<Cargo> listarCargos() {
		return cargoDAO.listAll();
	}
	
	public Cargo buscarCargoPorId(Long id) {
		return cargoDAO.findById(id);
	}

	public void removerCargo(Cargo cargo) {
		cargoDAO.removerCargo(cargo.getId());
	}
}
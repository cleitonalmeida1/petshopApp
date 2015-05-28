package br.ufms.cpcx.engweb.petshop.biz;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufms.cpcx.engweb.petshop.dao.AnimalDAO;
import br.ufms.cpcx.engweb.petshop.model.Animal;

@Stateless
public class CadastroAnimal {
	@EJB
	private AnimalDAO animalDAO;

	public Animal cadastrarAnimal(Animal animal) {
		return animalDAO.persist(animal);
	}

	public List<Animal> listarAnimais() {
		return animalDAO.listAll();
	}

	public void removerAnimal(Animal animal) {
		animalDAO.removerAnimal(animal.getId());
	}

}

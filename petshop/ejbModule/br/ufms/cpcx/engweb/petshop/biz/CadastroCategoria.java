package br.ufms.cpcx.engweb.petshop.biz;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufms.cpcx.engweb.petshop.dao.CategoriaDAO;
import br.ufms.cpcx.engweb.petshop.model.Categoria;

@Stateless
public class CadastroCategoria {
	@EJB
	private CategoriaDAO categoriaDAO;

	public Categoria cadastrarCategoria(Categoria categoria) {
		return categoriaDAO.persist(categoria);
	}

	public List<Categoria> listarCategorias() {
		return categoriaDAO.listAll();
	}
	
	public Categoria buscarCategoriaPorId(Long id) {
		return categoriaDAO.findById(id);
	}

	public void removerCategoria(Categoria categoria) {
		categoriaDAO.removerCategoria(categoria.getId());
	}

}

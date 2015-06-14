package br.ufms.cpcx.engweb.petshop.mb.cadastro;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.ufms.cpcx.engweb.petshop.biz.CadastroCategoria;
import br.ufms.cpcx.engweb.petshop.biz.CadastroFornecedor;
import br.ufms.cpcx.engweb.petshop.biz.CadastroFoto;
import br.ufms.cpcx.engweb.petshop.biz.CadastroMarca;
import br.ufms.cpcx.engweb.petshop.biz.CadastroProduto;
import br.ufms.cpcx.engweb.petshop.model.Categoria;
import br.ufms.cpcx.engweb.petshop.model.Fornecedor;
import br.ufms.cpcx.engweb.petshop.model.Foto;
import br.ufms.cpcx.engweb.petshop.model.Marca;
import br.ufms.cpcx.engweb.petshop.model.Produto;

@Named("cadastroProdutoMB")
@ConversationScoped
public class CadastroProdutoMB implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6856248927320753056L;
	private Produto produto;
	private Long idCategoria;
	private Long idMarca;
	private List<Marca> marcas;
	private List<Categoria> categorias;
	private Long idFornecedor;
	private List<Fornecedor> fornecedores;
	
	@EJB
	private CadastroProduto cadastroProduto;

	@EJB
	private CadastroFoto cadastroFoto;
	
	@EJB
	private CadastroMarca cadastroMarca;
	
	@EJB
	private CadastroCategoria cadastroCategoria;
	
	@EJB
	private CadastroFornecedor cadastroFornecedor;

	@Inject
	private Conversation conversation;
	
	

	@PostConstruct
	public void init() {
		// Carrega uma lista de Marcas para serem apresentadas no OneSelectMenu (combobox)
		marcas = cadastroMarca.listarMarcas();
		
		// Deixa nenhuma Categoria selecionada
		idMarca = null;
		
		// Carrega uma lista de Categorias para serem apresentadas no OneSelectMenu (combobox)
		categorias = cadastroCategoria.listarCategorias();
				
		// Deixa nenhuma Categoria selecionada
		idCategoria = null;
		
		// Carrega uma lista de Fornecedores para serem apresentados no OneSelectMenu (combobox)
		fornecedores = cadastroFornecedor.listarFornecedores();
		
		// Deixa nenhum Fornecedor selecionado
		idFornecedor = null;
				
		produto = new Produto();
	}

	public String salvar(UploadedFile arquivo) {
		Categoria categoria = cadastroCategoria.buscarCategoriaPorId(idCategoria);
		produto.setCategoria(categoria);
		
		Marca marca = cadastroMarca.buscarMarcaPorId(idMarca);
		produto.setMarca(marca);
		
		Fornecedor fornecedor = cadastroFornecedor.buscarFornecedorPorId(idFornecedor);
		produto.setFornecedor(fornecedor);
		
		if (arquivo != null) {
			Foto foto = new Foto();
			foto.setContent(arquivo.getContents());
			foto.setNome(arquivo.getFileName());
			foto.setTipo(arquivo.getContentType());
			foto = cadastroFoto.cadastrarFoto(foto);
			produto.setFoto(foto);
		}
		cadastroProduto.cadastrarProduto(produto);
		if (!conversation.isTransient()) {
			conversation.end();
		}
		return "listagemProdutos?faces-redirect=true";
	}

	public String cancelar() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
		return "listagemProdutos?faces-redirect=true";
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		if (produto.getId() != null && conversation.isTransient()) {
			conversation.begin();
		}
		this.produto = produto;
	}
	
	public Long getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(Long idMarca) {
		this.idMarca = idMarca;
	}

	public List<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	public Long getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Long idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
}

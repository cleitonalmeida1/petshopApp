package br.ufms.cpcx.engweb.petshop.mb.cadastro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.ufms.cpcx.engweb.petshop.biz.CadastroCidade;
import br.ufms.cpcx.engweb.petshop.biz.CadastroCliente;
import br.ufms.cpcx.engweb.petshop.biz.CadastroEndereco;
import br.ufms.cpcx.engweb.petshop.biz.CadastroEstado;
import br.ufms.cpcx.engweb.petshop.biz.CadastroPais;
import br.ufms.cpcx.engweb.petshop.biz.CadastroTelefone;
import br.ufms.cpcx.engweb.petshop.biz.CadastroTipoLogradouro;
import br.ufms.cpcx.engweb.petshop.model.Cidade;
import br.ufms.cpcx.engweb.petshop.model.Cliente;
import br.ufms.cpcx.engweb.petshop.model.Endereco;
import br.ufms.cpcx.engweb.petshop.model.Estado;
import br.ufms.cpcx.engweb.petshop.model.Pais;
import br.ufms.cpcx.engweb.petshop.model.Telefone;
import br.ufms.cpcx.engweb.petshop.model.TipoLogradouro;
import br.ufms.cpcx.engweb.petshop.model.enuns.TipoEnderecoEnum;
import br.ufms.cpcx.engweb.petshop.model.enuns.TipoPessoaEnum;
 
@ManagedBean(name="cadastroClienteMB")
@ViewScoped
public class CadastroClienteMB implements Serializable {

	private static final long serialVersionUID = 8769059380500459231L;
    
	private Long idCliente;
    private Cliente cliente;

	private Telefone telefone;

	private TipoEnderecoEnum tipoEndereco;
    private String tipoEnderecoString;
    
    private TipoPessoaEnum tipoPessoa;
    private String tipoPessoaString;

	private Endereco endereco;
    private Long idEndereco;

	private TipoLogradouro tipoLogradouro;
    private List<TipoLogradouro> tiposDeLogradouros;
    
    private Pais pais;
    private List<Pais> paises;
    private Long idPais;
    
    private Estado estado;
    private List<Estado> estados;
    private Long idEstado;
    
    private Cidade cidade;
    private List<Cidade> cidades;
    private Long idCidade;

	@EJB
    CadastroCliente cadastroCliente;
	
	@EJB
    CadastroEndereco cadastroEndereco;
	
	@EJB
    CadastroTelefone cadastroTelefone;
	
	@EJB
    CadastroTipoLogradouro cadastroTipoLogradouro;
	
	@EJB
    CadastroPais cadastroPais;
	
	@EJB
    CadastroEstado cadastroEstado;
	
	@EJB
    CadastroCidade cadastroCidade;
	
	@Inject
	private Conversation conversation;
     
	@PostConstruct
    public void init() {
    	
    	String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
       
        if(id != null){
        	idCliente = Long.valueOf(id);
        }
    	if(idCliente != null){
    		cliente = cadastroCliente.buscarClientePorId(idCliente);
    		
    	}else{
    		cliente = new Cliente();
    	}
    	
    	if(cliente.getId() != null && cliente.getTelefone() != null){
    		telefone = cliente.getTelefone();
    	}else{
    		telefone = new Telefone();
    	}
    	
    	tiposDeLogradouros = cadastroTipoLogradouro.listarTiposDeLogradouros();
    	
    	if(cliente.getId() != null &&  cliente.getEndereco() != null){
    		endereco = cliente.getEndereco();
    	}else{
    		endereco = new Endereco();
    	}
    	
    	paises = cadastroPais.listarPaises();
    	
    	if(cliente.getId() != null &&  cliente.getEndereco().getCidade().getEstado().getPais() != null){
    		this.setPais(cliente.getEndereco().getCidade().getEstado().getPais());
    		estados = cadastroEstado.listarEstadosPorIdPais(this.pais.getId());
    	}else{
    		pais = new Pais();
    		estados = new ArrayList<Estado>();
    	}
    	
    	if(cliente.getId() != null &&  cliente.getEndereco().getCidade().getEstado() != null){
    		this.setEstado(cliente.getEndereco().getCidade().getEstado());
    		cidades = cadastroCidade.listarCidadesPorIdEstado(this.estado.getId());
    	}else{
    		estado = new Estado();
    		cidades = new ArrayList<Cidade>();
    	}
    	
    	if(cliente.getId() != null &&  cliente.getEndereco().getCidade() != null){
    		this.setCidade(cliente.getEndereco().getCidade());
    	}else{
    		cidade = new Cidade();
    	}
    	
    	tipoLogradouro = new TipoLogradouro();
    	if(cliente.getId() != null &&  cliente.getEndereco().getTipoLogradouro() != null){
    		this.setTipoLogradouro(cliente.getEndereco().getTipoLogradouro());
    	}
    	
    	if(cliente.getId() != null && cliente.getEndereco().getTipoEndereco() != null){
    		tipoEnderecoString = cliente.getEndereco().getTipoEndereco().name();
    	}else{
    		tipoEnderecoString = new String();
    	}
    	
    	if(cliente.getId() != null && cliente.getTipoPessoa() != null){
    		tipoPessoaString = cliente.getTipoPessoa().name();
    	}else{
    		tipoPessoaString = new String();
    	}
    }
    
    public String salvar() {
    	//Setar Tipo de Pessoa
    	cliente.setTipoPessoa(procurarTipoPessoaEnumNaLista(this.tipoPessoaString));
    	
		//Setar Telefone
    	if(cliente.getId() == null){
    		telefone = cadastroTelefone.cadastrarTelefone(telefone);
    	}
		cliente.setTelefone(this.telefone);
		
		//Setando a cidade, pois elá ja consta no Banco de Dados
		this.cidade = cadastroCidade.buscarCidadePorId(this.cidade.getId());
		this.endereco.setCidade(this.cidade);
		
		//setar o tipo de Endereço
		this.endereco.setTipoEndereco(procurarTipoDeEnderecoEnumNaLista(this.tipoEnderecoString));
		
		//Setando o Tipo de Logradouro, pois elá ja consta no Banco de Dados
		TipoLogradouro tipoLogradouro = cadastroTipoLogradouro.buscarTipoLogradouroPorId(this.tipoLogradouro.getId());
		this.endereco.setTipoLogradouro(tipoLogradouro);
		
		//Salvando o endereço
		if(cliente.getId() == null){
			this.endereco = cadastroEndereco.cadastrarEndereco(this.endereco);
		}
		
		cliente.setEndereco(this.endereco);
		
		//Salvar o Cliente
		cliente = cadastroCliente.cadastrarCliente(cliente);
		
		return "listagemClientes?faces-redirect=true";
	}
    
    public TipoEnderecoEnum procurarTipoDeEnderecoEnumNaLista(String rotulo){
    	TipoEnderecoEnum[] tiposDeEnderecos = TipoEnderecoEnum.values();
    	for (TipoEnderecoEnum t : tiposDeEnderecos) {
			if (t.name().equals(rotulo)) {
				return t;
			}
		}
    	return null;
    }
    
    public TipoPessoaEnum procurarTipoPessoaEnumNaLista(String rotulo){
    	TipoPessoaEnum[] tiposDePessoas = TipoPessoaEnum.values();
    	for (TipoPessoaEnum t : tiposDePessoas) {
			if (t.name().equals(rotulo)) {
				return t;
			}
		}
    	return null;
    }
    
    public String cancelar(){
    	
		return "listagemFornecedores?faces-redirect=true";
	}
    
    public void onPaisChange() {
        estado = new Estado();
    	cidade = new Cidade();
    	if(pais !=null && !pais.equals("")){
            estados = cadastroEstado.listarEstadosPorIdPais(pais.getId());
            cidades = new ArrayList<Cidade>();
    	}else{
            estados = new ArrayList<Estado>();
    	}
    }
    
    public void onEstadoChange() {
    	cidade = new Cidade();
    	if(estado !=null && !estado.equals("")){
            cidades = cadastroCidade.listarCidadesPorIdEstado(estado.getId());
    	}else{
            cidades = new ArrayList<Cidade>();
    	}
    }
     
    public void displayLocation() {
        FacesMessage msg;
        if(pais != null && estado != null && cidade != null)
            msg = new FacesMessage("Selecionado:", cidade.getNome() + " (" + estado.getNome()+") - "+pais.getNome());
        else
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Inválido", "País, Estado e Cidadão foram selecionados."); 
             
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    
    public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
    
    public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
    
    public TipoPessoaEnum getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoaEnum tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getTipoPessoaString() {
		return tipoPessoaString;
	}

	public void setTipoPessoaString(String tipoPessoaString) {
		this.tipoPessoaString = tipoPessoaString;
	}
    
    public Long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}
    
    
    public List<Pais> getPaises() {
		return paises;
	}

	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Long getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Long idCidade) {
		this.idCidade = idCidade;
	}

	public Long getIdPais() {
		return idPais;
	}

	public void setIdPais(Long idPais) {
		this.idPais = idPais;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public TipoEnderecoEnum getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(TipoEnderecoEnum tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public TipoLogradouro getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public List<TipoLogradouro> getTiposDeLogradouros() {
		return tiposDeLogradouros;
	}

	public void setTiposDeLogradouros(List<TipoLogradouro> tiposDeLogradouros) {
		this.tiposDeLogradouros = tiposDeLogradouros;
	}

	public String getTipoEnderecoString() {
		return tipoEnderecoString;
	}

	public void setTipoEnderecoString(String tipoEnderecoString) {
		this.tipoEnderecoString = tipoEnderecoString;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
}
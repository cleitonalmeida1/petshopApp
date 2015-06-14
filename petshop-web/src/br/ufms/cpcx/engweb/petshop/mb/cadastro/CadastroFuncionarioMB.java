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

import br.ufms.cpcx.engweb.petshop.biz.CadastroCargo;
import br.ufms.cpcx.engweb.petshop.biz.CadastroCidade;
import br.ufms.cpcx.engweb.petshop.biz.CadastroEndereco;
import br.ufms.cpcx.engweb.petshop.biz.CadastroEstado;
import br.ufms.cpcx.engweb.petshop.biz.CadastroFuncionario;
import br.ufms.cpcx.engweb.petshop.biz.CadastroPais;
import br.ufms.cpcx.engweb.petshop.biz.CadastroTipoLogradouro;
import br.ufms.cpcx.engweb.petshop.model.Cargo;
import br.ufms.cpcx.engweb.petshop.model.Cidade;
import br.ufms.cpcx.engweb.petshop.model.Endereco;
import br.ufms.cpcx.engweb.petshop.model.Estado;
import br.ufms.cpcx.engweb.petshop.model.Funcionario;
import br.ufms.cpcx.engweb.petshop.model.Pais;
import br.ufms.cpcx.engweb.petshop.model.TipoLogradouro;
import br.ufms.cpcx.engweb.petshop.model.enuns.TipoEnderecoEnum;
 
@ManagedBean(name="cadastroFuncionarioMB")
@ViewScoped
public class CadastroFuncionarioMB implements Serializable {

	private static final long serialVersionUID = 8769059380500459231L;
    
    private Funcionario funcionario;
    
    private Cargo cargo;
    private List<Cargo> cargos;
    
    private String telefone;
    
    private TipoEnderecoEnum tipoEndereco;
    private String tipoEnderecoString;
    
    private Endereco endereco;
    private Long idEndereco;

	private TipoLogradouro tipoLogradouro;
	private String tipoLogradouroString;
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

	@EJB
    CadastroFuncionario cadastroFuncionario;
	
	@EJB
    CadastroEndereco cadastroEndereco;
	
	@EJB
    CadastroCargo cadastroCargo;
	
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
    	Long idFuncionario = null;
    	String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
       
        if(value != null){
        	idFuncionario = Long.valueOf(value);
        }
    	if(idFuncionario != null){
    		funcionario = cadastroFuncionario.buscarFuncionarioPorId(idFuncionario);
    	}else{
    		funcionario = new Funcionario();
    	}
    	
    	if(funcionario.getId() != null && funcionario.getCargo() != null){
    		cargo = funcionario.getCargo();
    	}else{
    		cargo = new Cargo();
    	}
    	
    	if(funcionario.getId() != null && funcionario.getTelefone() != null){
    		telefone = funcionario.getTelefone();
    	}else{
    		telefone = new String();
    	}
    	
    	tiposDeLogradouros = cadastroTipoLogradouro.listarTiposDeLogradouros();
    	
    	cargos = cadastroCargo.listarCargos();
    	
    	if(funcionario.getId() != null &&  funcionario.getEndereco() != null){
    		endereco = funcionario.getEndereco();
    	}else{
    		endereco = new Endereco();
    	}
    	
    	paises = cadastroPais.listarPaises();
    	
    	
    	if(funcionario.getId() != null &&  funcionario.getEndereco().getCidade().getEstado().getPais() != null){
    		this.setPais(funcionario.getEndereco().getCidade().getEstado().getPais());
    		estados = cadastroEstado.listarEstadosPorIdPais(this.pais.getId());
    	}else{
    		pais = new Pais();
    		estados = new ArrayList<Estado>();
    	}
    	
    	if(funcionario.getId() != null &&  funcionario.getEndereco().getCidade().getEstado() != null){
    		this.setEstado(funcionario.getEndereco().getCidade().getEstado());
    		cidades = cadastroCidade.listarCidadesPorIdEstado(this.estado.getId());
    	}else{
    		estado = new Estado();
    		cidades = new ArrayList<Cidade>();
    	}
    	
    	if(funcionario.getId() != null &&  funcionario.getEndereco().getCidade() != null){
    		this.setCidade(funcionario.getEndereco().getCidade());
    	}else{
    		cidade = new Cidade();
    	}
    	
    	tipoLogradouro = new TipoLogradouro();
    	if(funcionario.getId() != null &&  funcionario.getEndereco().getTipoLogradouro() != null){
    		this.setTipoLogradouro(funcionario.getEndereco().getTipoLogradouro());
    	}
    	
    	
    	
    	if(funcionario.getId() != null && funcionario.getEndereco().getTipoEndereco() != null){
    		tipoEnderecoString = funcionario.getEndereco().getTipoEndereco().name();
    	}else{
    		tipoEnderecoString = new String();
    	}
    }
    
    public String salvar() {
		//Setar Telefone
		funcionario.setTelefone(this.telefone);
		
		//Setando a cidade, pois elá ja consta no Banco de Dados
		this.cidade = cadastroCidade.buscarCidadePorId(this.cidade.getId());
		this.endereco.setCidade(this.cidade);
		
		//setar o tipo de Endereço
		this.endereco.setTipoEndereco(procurarTipoDeEnderecoEnumNaLista(this.tipoEnderecoString));
		
		//setar o cargo
		cargo = cadastroCargo.buscarCargoPorId(cargo.getId());
		funcionario.setCargo(cargo);
		
		//Setando o Tipo de Logradouro, pois elá ja consta no Banco de Dados
		TipoLogradouro tipoLogradouro = cadastroTipoLogradouro.buscarTipoLogradouroPorId(this.tipoLogradouro.getId());
		this.endereco.setTipoLogradouro(tipoLogradouro);
		
		//Salvando o endereço
		this.endereco = cadastroEndereco.cadastrarEndereco(this.endereco);
		funcionario.setEndereco(this.endereco);
		
		//Salvar o funcionário
		cadastroFuncionario.cadastrarFuncionario(funcionario);
		
		return "listagemFuncionarios?faces-redirect=true";
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

	public String getTipoLogradouroString() {
		return tipoLogradouroString;
	}

	public void setTipoLogradouroString(String tipoLogradouroString) {
		this.tipoLogradouroString = tipoLogradouroString;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTipoEnderecoString() {
		return tipoEnderecoString;
	}

	public void setTipoEnderecoString(String tipoEnderecoString) {
		this.tipoEnderecoString = tipoEnderecoString;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}
}
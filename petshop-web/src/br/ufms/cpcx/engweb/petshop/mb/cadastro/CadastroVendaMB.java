package br.ufms.cpcx.engweb.petshop.mb.cadastro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import br.ufms.cpcx.engweb.petshop.biz.CadastroCategoria;
import br.ufms.cpcx.engweb.petshop.biz.CadastroCidade;
import br.ufms.cpcx.engweb.petshop.biz.CadastroCliente;
import br.ufms.cpcx.engweb.petshop.biz.CadastroFuncionario;
import br.ufms.cpcx.engweb.petshop.biz.CadastroMarca;
import br.ufms.cpcx.engweb.petshop.biz.CadastroProduto;
import br.ufms.cpcx.engweb.petshop.biz.CadastroVenda;
import br.ufms.cpcx.engweb.petshop.model.Cargo;
import br.ufms.cpcx.engweb.petshop.model.Categoria;
import br.ufms.cpcx.engweb.petshop.model.Cliente;
import br.ufms.cpcx.engweb.petshop.model.Funcionario;
import br.ufms.cpcx.engweb.petshop.model.ItemVenda;
import br.ufms.cpcx.engweb.petshop.model.Marca;
import br.ufms.cpcx.engweb.petshop.model.Produto;
import br.ufms.cpcx.engweb.petshop.model.Venda;
 
@ManagedBean(name="cadastroVendaMB")
@ViewScoped
public class CadastroVendaMB implements Serializable {

	private static final long serialVersionUID = 5755890558623675058L;
	
    private Cliente cliente;
    private Long idCliente;
    private List<Cliente> clientes;
    
    private Venda venda;
    private Long idVenda;
    private List<Venda> vendas;
    private List<ItemVenda> itensVenda;
    private ItemVenda itemVenda;
    
    private Funcionario vendedor;
    private Long idVendedor;
    private List<Funcionario> funcionarios;
    private Cargo cargo;
    private Long idCargo;
    private List<Cargo> cargos;
    
    private BigDecimal valorVenda;
    
    private List<Marca> marcas;
    private List<Categoria> categorias;
    private List<Produto> produtos;
    private Produto produto;
    private Long idProduto;
    private Integer qtde;
    
    private boolean mostrarTabela = true;
    private boolean novavenda = true;

	@EJB
    CadastroVenda cadastroVenda;
	
	@EJB
    CadastroCliente cadastroCliente;
	
	@EJB
    CadastroProduto cadastroProduto;
	
	@EJB
    CadastroMarca cadastroMarca;
	
	@EJB
    CadastroCategoria cadastroCategoria;
	
	@EJB
    CadastroFuncionario cadastroFuncionario;
	
	@EJB
    CadastroCargo cadastroCargo;
	
	@EJB
    CadastroCidade cadastroCidade;
	
	@Inject
	private Conversation conversation;
     
	@PostConstruct
    public void init() {
    	
    	String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
       
        if(id != null){
        	idVenda = Long.valueOf(id);
        }
        
    	if(idVenda != null){
    		venda = cadastroVenda.buscarVendaPorId(idVenda);
    		
    	}else{
    		venda = new Venda();
    	}
    	
    	if(venda.getId() == null){
    		novavenda = false;
    	}
    	
    	if(venda.getId() != null && venda.getCliente() != null){
    		cliente = venda.getCliente();
    		idCliente = venda.getCliente().getId();
    	}else{
    		cliente = new Cliente();
    	}
    	
    	//carregar clientes
    	if(clientes == null){
    		clientes = cadastroCliente.listarClientes();
    	}
    	
    	//carregar cargos
    	if(cargos == null){
    		cargos = cadastroCargo.listarCargos();
    	}
    	
    	
    	
    	if(venda.getId() != null && venda.getVendedor() != null){
    		vendedor = venda.getVendedor();
    		idVendedor = vendedor.getId();
    		idCargo = venda.getVendedor().getCargo().getId();
    		funcionarios = cadastroFuncionario.buscarFuncionariosPorIdCargo(idCargo);
    	}else{
    		vendedor = new Funcionario();
    	}
    	
    	//Tipo de Pagamento
    	
    	if(venda.getId() != null && venda.getItensVenda() != null){
    		itensVenda = venda.getItensVenda();
    	}else{
    		itensVenda = new ArrayList<ItemVenda>();
    	}
    	
    	if(itensVenda.size() > 0){
    		setMostrarTabela(true);
    	}
    }
    
	public void diminuirProdutoNoEstoque(List<ItemVenda> itensVenda){
		for (int i = 0; i < itensVenda.size(); i++) {
			cadastroProduto.diminuirProdutoDoEstoque(itensVenda.get(i).getProduto().getId(), itensVenda.get(i).getQtde());
		}
	}
	
	public String textoBotaoCancelarVoltar(){
		if(venda.getId() == null){
			return "Cancelar";
		}
		return "Voltar";
	}
	
    public String salvar() {
    	//Setar o cliente
    	
    	cliente = cadastroCliente.buscarClientePorId(idCliente);
    	venda.setCliente(cliente);
    	
    	//Setar o Vendedor
    	vendedor = cadastroFuncionario.buscarFuncionarioPorId(idVendedor);
    	venda.setVendedor(vendedor);
    	
    	//Setar os Itens Venda
    	venda.setItensVenda(itensVenda);
    	
    	//Setar a Data e a Hora da Venda
    	if(venda.getDatahoravenda() == null){
    		venda.setDatahoravenda(new Date());
    	}
    	if(venda.getId() == null){
    		diminuirProdutoNoEstoque(itensVenda);
    	}
    	
    	//Salvar a Venda
    	cadastroVenda.cadastrarVenda(venda);
		
		return "listagemVendas?faces-redirect=true";
	}
    
    public String cancelar(){
		return "listagemVendas?faces-redirect=true";
	}
    
    public boolean produtoExisteNaLista(Long idProduto){
    	for (int i = 0; i < itensVenda.size(); i++) {
			if(itensVenda.get(i).getProduto().getId().longValue() == idProduto.longValue()){
				return true;
			}
		}
    	return false;
    }
    
    public void adicionarItemVenda() { //depois da execução deste método, atualizar a tabela de itens
    	FacesMessage msg;
    	if(idProduto != null & !idProduto.equals("") & qtde != null & !qtde.equals("")){
    		//verificar se o produto ja existe nos ítens da venda, caso exista não adicionar
    		if(!produtoExisteNaLista(idProduto)){ 
    			adicionarProduto(idProduto, qtde);
    		}else{
    			//erro, o produto já existe na lista
    			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro. Produto já foi adicionado. Edite ou remova-o.", ""); 
                FacesContext.getCurrentInstance().addMessage(null, msg);
    		}
    	}else{
    		//Estourar mensagem de erro na tela pra informar o produto corretamente
    		msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro. Selecione o Produto e informe a quantidade.", ""); 
            FacesContext.getCurrentInstance().addMessage(null, msg);
    	}
    }
    
    public void editarItemVenda(ItemVenda itemVenda) { //depois da execução deste método, atualizar a tabela de itens
    	FacesMessage msg;
    	if(itemVenda.getProduto().getId() != null & !itemVenda.getProduto().getId().equals("")){
    		editarProduto(itemVenda.getProduto().getId(), itemVenda.getQtde());
    	}else{
    		//Estourar mensagem de erro na tela pra informar o produto corretamente
    		msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro. Produto e quantidade não foram informados.", ""); 
            FacesContext.getCurrentInstance().addMessage(null, msg);
    	}
    }
    
    public void removerItemVenda(ItemVenda itemVenda) { //depois da execução deste método, atualizar a tabela de itens
    	FacesMessage msg;
    	if(itemVenda.getProduto().getId() != null & !itemVenda.getProduto().getId().equals("")){
    		//verificar se o produto ja existe nos ítens da venda, caso exista não adicionar
    		if(produtoExisteNaLista(itemVenda.getProduto().getId())){
    			removerProduto(itemVenda.getProduto().getId());
    		}else{
    			//erro, o produto já existe na lista
    			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro. Produto não foi adicionado.", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
    		}
    	}else{
    		//Estourar mensagem de erro na tela pra informar o produto corretamente
    		msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro. Selecione o Produto e informe a quantidade.", ""); 
            FacesContext.getCurrentInstance().addMessage(null, msg);
    	}
    }
    
    public void adicionarProduto(Long idProduto, Integer qtde){
    	produto = cadastroProduto.buscarProdutoPorId(idProduto);
    	if(qtde.intValue() <= produto.getQtde().longValue()){
    		//compor o item Venda
    		ItemVenda novoitem = new ItemVenda();
    		novoitem.setProduto(produto); //Produto
    		//Manter Dados estáticos do produto para evitar percar de dados caso haja atualizações futuras no mesmo, como alteração de preço e nome.
    		novoitem.setNome(produto.getNome()); //Nome do Produto
    		novoitem.setQtde(qtde); //Quantidade do Produto
    		novoitem.setValorUnitario(produto.getValorUnitario()); //Valor Unitário do Produto
    		novoitem.setValorTotal(novoitem.getValorUnitario().multiply(new BigDecimal(novoitem.getQtde()))); //Valor Total do Item Venda
    		//Adicionando na Tabela
    		atualizaValorTotalVenda(novoitem.getValorTotal());
    		itensVenda.add(novoitem);
    	}else{ //Erro, não existe a quantidade suficiente do produto no Estoque
    		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro. Produto não contêm a quantidade solicitada em Estoque. Quantidade em Estoque: "+produto.getQtde(), ""); 
            FacesContext.getCurrentInstance().addMessage(null, msg); 
    	}
		
    }
    
    public void editarProduto(Long idProduto, Integer qtde){
    	this.idProduto = idProduto;
    	this.qtde = qtde; 
    }
    
    public void removerProduto(Long idProduto){
    	for (int i = 0; i < itensVenda.size(); i++) {
    		if(itensVenda.get(i).getProduto().getId().longValue() == idProduto.longValue()){
    			atualizaValorTotalVenda(itensVenda.get(i).getValorTotal().multiply(new BigDecimal(-1)));
    			itensVenda.remove(i);
			}
		}   
    }
    
    public void atualizaValorTotalVenda(BigDecimal novoValor){
    	if(venda.getValorTotal() == null){
    		venda.setValorTotal(novoValor);
    	}else{
    		venda.setValorTotal(venda.getValorTotal().add(novoValor));
    	}
    }
    
    public void carregarProdutos(){
    	//carregar produtos
    	idProduto = null;
    	qtde = null;
    	produtos = cadastroProduto.listarProdutos();
    }
    
    public void onCargoChange() {
    	if(idCargo != null & !idCargo.equals("")){
    		funcionarios = cadastroFuncionario.buscarFuncionariosPorIdCargo(idCargo);
    	}else{
    		funcionarios = new ArrayList<Funcionario>();
    	}
    }

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Long getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(Long idVenda) {
		this.idVenda = idVenda;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}

	public Funcionario getVendedor() {
		return vendedor;
	}

	public void setVendedor(Funcionario vendedor) {
		this.vendedor = vendedor;
	}

	public Long getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(Long idVendedor) {
		this.idVendedor = idVendedor;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Long getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(Long idCargo) {
		this.idCargo = idCargo;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}

	public List<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public Integer getQtde() {
		return qtde;
	}

	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}

	public boolean isMostrarTabela() {
		return mostrarTabela;
	}

	public void setMostrarTabela(boolean mostrarTabela) {
		this.mostrarTabela = mostrarTabela;
	}

	public ItemVenda getItemVenda() {
		return itemVenda;
	}

	public void setItemVenda(ItemVenda itemVenda) {
		this.itemVenda = itemVenda;
	}

	public boolean isNovavenda() {
		return novavenda;
	}

	public void setNovavenda(boolean novavenda) {
		this.novavenda = novavenda;
	}
}
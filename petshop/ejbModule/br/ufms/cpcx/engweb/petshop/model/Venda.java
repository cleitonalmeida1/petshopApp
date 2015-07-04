package br.ufms.cpcx.engweb.petshop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_venda")
public class Venda implements Serializable {

	private static final long serialVersionUID = -8991028263543131376L;
	@Id
	@SequenceGenerator(name = "venda_seq_gen", sequenceName = "venda_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "venda_seq_gen", strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private Long id;
	// cliente
	@ManyToOne(optional = false)
 	@JoinColumn(name = "id_cliente")
	private Cliente Cliente;
	// vendedor
	@ManyToOne(optional = false)
 	@JoinColumn(name = "id_vendedor")
	private Funcionario Vendedor;

	@Basic(optional = false)
	//@NotNull
	@Column(name = "datahoravenda", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date datahoravenda;
	
	private String observacao;
	
	@NotNull
	private BigDecimal valorTotal;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinTable(name = "venda_itensvenda", joinColumns = { @JoinColumn(name = "VENDA_ID", referencedColumnName = "id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "ITEMVENDA_ID", referencedColumnName = "id", nullable = false) })
	private List<ItemVenda> itensVenda;
	
	public String getDataHoraString(Date data){
		return new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(data);
	}
	
	public String getDataString(Date data){
		return new SimpleDateFormat("dd/MM/yyyy").format(data);
	}
	
	public String getHoraString(Date data){
		return new SimpleDateFormat("HH:mm").format(data);
	}
	

	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}

	public Venda() {
	}

	public Venda(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return Cliente;
	}

	public void setCliente(Cliente cliente) {
		Cliente = cliente;
	}

	public Funcionario getVendedor() {
		return Vendedor;
	}

	public void setVendedor(Funcionario vendedor) {
		Vendedor = vendedor;
	}

	public Date getDatahoravenda() {
		return datahoravenda;
	}

	public void setDatahoravenda(Date datahoravenda) {
		this.datahoravenda = datahoravenda;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
}
package br.ufms.cpcx.engweb.petshop.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_item_venda")
public class ItemVenda implements Serializable {

	private static final long serialVersionUID = 444800789786099858L;
	@Id
	@SequenceGenerator(name = "item_venda_seq_gen", sequenceName = "item_venda_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "item_venda_seq_gen", strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private Long id;
	// produto
	@ManyToOne(optional = false)
 	@JoinColumn(name = "id_produto")
	private Produto produto;
	private String nome;
	@Column(nullable = false)
	private Integer qtde;
	@Column(nullable = false)
	private BigDecimal valorUnitario;
	@Column(nullable = false)
	private BigDecimal valorTotal;

	public ItemVenda() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQtde() {
		return qtde;
	}

	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
}
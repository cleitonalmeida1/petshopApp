package br.ufms.cpcx.engweb.petshop.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
    //produto
    @OneToOne(optional = false)
    private Produto idProduto;
    @Column(nullable=false)
    private Long qtde;
    @Column(nullable=false)
    private BigDecimal valorUnitario;
    @Column(nullable=false)  
    private double desconto;
    @Column(nullable=false)  
    private double valorTotal;
    
    
    public ItemVenda() {
    }

    public ItemVenda(Long id) {
        this.id = id;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Produto idProduto) {
		this.idProduto = idProduto;
	}

	public Long getQtde() {
		return qtde;
	}

	public void setQtde(Long qtde) {
		this.qtde = qtde;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

    
	
}


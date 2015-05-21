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
	
	
	 //venda
	@OneToOne(optional = false)
	private Venda idVenda;
    //produto
    @OneToOne(optional = false)
    private Produto idProduto;
    
    @Column(name = "qtde", nullable = false)
	private Long qtde;
    
    @Column(name = "valor_unitario", precision = 10, scale = 2)
    private BigDecimal valorUnitario;
    
    
    public ItemVenda() {
    }

    public ItemVenda(Long id) {
        this.id = id;
    }

	
}


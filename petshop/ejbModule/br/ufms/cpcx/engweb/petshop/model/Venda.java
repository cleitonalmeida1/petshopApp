package br.ufms.cpcx.engweb.petshop.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_venda")
public class Venda implements Serializable {
	
	private static final long serialVersionUID = -2267294025210623147L;
	
	@Id
	@SequenceGenerator(name = "venda_seq_gen", sequenceName = "venda_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "venda_seq_gen", strategy = GenerationType.SEQUENCE)
	
	private Long id;
	 //cliente
	@OneToOne(optional = false)
	private Cliente Cliente;
    //vendedor
    @OneToOne(optional = false)
    private Funcionario Vendedor;
	
    @Basic(optional = false)
    @NotNull
    @Column(name = "datahoravenda", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datahoravenda;
    
    //@ManyToMany(mappedBy = "tb_item_venda")
	//private List<ItemVenda> itensVenda;
    
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
}


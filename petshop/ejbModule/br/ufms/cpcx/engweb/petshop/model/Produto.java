package br.ufms.cpcx.engweb.petshop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = -678578191724165487L;

	@Id
	@SequenceGenerator(name = "produto_seq_gen", sequenceName = "produto_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "produto_seq_gen", strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private Long id;
	
    @Size(max = 100)
    @Column(name = "nome", length = 100)
    private String nome;
    
    @Size(max = 250)
    @Column(name = "descricao", length = 250)
    private String descricao;
    
    
    @Size(max = 100)
    @Column(name = "tipo_unidade", length = 100)
    private String tipoUnidade;
    
    @Column(name = "valor_unitario", precision = 10, scale = 2)
    private BigDecimal valorUnitario;
    
    @Size(max = 100)
    @Column(name = "marca", length = 100)
    private String marca;
    
    //id categoria
    @JoinColumn(name = "id_categoria", referencedColumnName = "id", nullable = false)
	@OneToOne(optional = false)
    private Categoria Categoria;
    
    //id fornecedor
    @JoinColumn(name = "id_fornecedor", referencedColumnName = "id", nullable = false)
	@OneToOne(optional = false)
    private Fornecedor Fornecedor;
    
    @Column(name = "qtde", nullable = false)
	private Long qtde;
    
    public Produto() {
    	
    }

    public Produto(Long id) {
        this.id = id;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipoUnidade() {
		return tipoUnidade;
	}

	public void setTipoUnidade(String tipoUnidade) {
		this.tipoUnidade = tipoUnidade;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Categoria getCategoria() {
		return Categoria;
	}

	public void setCategoria(Categoria categoria) {
		Categoria = categoria;
	}

	public Fornecedor getFornecedor() {
		return Fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		Fornecedor = fornecedor;
	}

	public Long getQtde() {
		return qtde;
	}

	public void setQtde(Long qtde) {
		this.qtde = qtde;
	}
}


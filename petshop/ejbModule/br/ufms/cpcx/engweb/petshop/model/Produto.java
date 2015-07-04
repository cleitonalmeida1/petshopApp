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
import javax.persistence.Transient;
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
    private String nome;
    @Transient //não mapear este atributo - apenas como regra de negócio
	private final int tamMaxDescricao = 1000;
    @Size(max = tamMaxDescricao)
    private String descricao;
    private String tipoUnidade;
    private BigDecimal valorUnitario;
    
    @ManyToOne(optional = false)
 	@JoinColumn(name = "id_marca")
    private Marca marca;

	//id Foto
    @ManyToOne(optional = true)
 	@JoinColumn(name = "id_foto")
    private Foto foto;
    
    //id categoria
    @ManyToOne(optional = false)
 	@JoinColumn(name = "id_categoria")
    private Categoria categoria;
    
    //id fornecedor
    @ManyToOne(optional = false)
 	@JoinColumn(name = "id_fornecedor")
    private Fornecedor fornecedor;
    
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

	public int getTamMaxDescricao() {
		return tamMaxDescricao;
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
	
	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
    public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Long getQtde() {
		return qtde;
	}

	public void setQtde(Long qtde) {
		this.qtde = qtde;
	}
}
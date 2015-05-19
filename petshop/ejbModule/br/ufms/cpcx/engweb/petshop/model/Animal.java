package br.ufms.cpcx.engweb.petshop.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_cliente")
public class Animal implements Serializable {

	private static final long serialVersionUID = -3052883518527417260L;
	
	@Id
	@SequenceGenerator(name = "animal_seq_gen", sequenceName = "animal_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "animal_seq_gen", strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private Long id;
	
    @Size(max = 100)
    @Column(name = "nome", length = 100)
    private String nome;
    
    @Size(max = 15)
    @Column(name = "raca", length = 15)
    private String raca;
    
    
    @Column(name = "peso", length = 40)
    private double peso;
    
    @Size(max = 15)
    @Column(name = "cor", length = 40)
    private String cor;
    
    @NotNull
    @Column(name = "datanascimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date datanascimento;
    
    @Size(max = 1)
    @Column(name = "sexo", length = 1)
    private String sexo;
    
    //telefone
    @JoinColumn(name = "id_cliente", referencedColumnName = "id", nullable = false)
	@OneToOne(optional = false)
	private Telefone cliente;

    public Animal(){
    	
    }
    
    public Animal(Long id) {
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

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Date getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Telefone getCliente() {
		return cliente;
	}

	public void setCliente(Telefone cliente) {
		this.cliente = cliente;
	}

	    
    
}


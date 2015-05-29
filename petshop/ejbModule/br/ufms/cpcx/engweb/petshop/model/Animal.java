package br.ufms.cpcx.engweb.petshop.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_animal")
public class Animal implements Serializable {

	private static final long serialVersionUID = -3052883518527417260L;
	
	@Id
	@SequenceGenerator(name = "animal_seq_gen", sequenceName = "animal_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "animal_seq_gen", strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private Long id;
	
    private String nome;
    private String raca;
    private double peso;
    private String cor;
    private Date datanascimento;
    private String sexo;
    @OneToOne
    private Foto foto;
    @OneToOne
    private Cliente cliente;
    
    public Animal(){
    	
    }
    
    public Animal(Long id) {
        this.id = id;
    }

	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
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

}


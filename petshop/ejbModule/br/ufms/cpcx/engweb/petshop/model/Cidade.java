package br.ufms.cpcx.engweb.petshop.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "tb_cidade")
@XmlRootElement
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "cidade_seq_gen", sequenceName = "cidade_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "cidade_seq_gen", strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private Long id;

	@Size(max = 60)
	@Column(name = "nome", length = 60)
	private String nome;

	@OneToOne
	private Estado Estado;

	public Cidade(){
		
	}
	
	public Cidade(Long id){
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


	public Estado getEstado() {
		return Estado;
	}


	public void setEstado(Estado estado) {
		Estado = estado;
	}

	

}

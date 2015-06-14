package br.ufms.cpcx.engweb.petshop.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_marca")
public class Marca implements Serializable{

	private static final long serialVersionUID = 7045067616591098231L;
	@Id
	@SequenceGenerator(name = "marca_seq_gen", sequenceName = "marca_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "marca_seq_gen", strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private Long id;
    private String nome;
	
	public Marca(){
		
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
}
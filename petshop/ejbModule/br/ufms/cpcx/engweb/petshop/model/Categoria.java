package br.ufms.cpcx.engweb.petshop.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_categoria")
public class Categoria implements Serializable{

	private static final long serialVersionUID = -9052168237814382046L;
	
	@Id
	@SequenceGenerator(name = "categoria_seq_gen", sequenceName = "categoria_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "categoria_seq_gen", strategy = GenerationType.SEQUENCE)

	private Long id;
    private String nome;
    private String descricao;

    public Categoria() {
        
    }

    public Categoria(Long id) {
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
    
    
}

package br.ufms.cpcx.engweb.petshop.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import br.ufms.cpcx.engweb.petshop.model.interfaces.SampleEntity;

@Entity
@Table(name = "tb_cidade")
@XmlRootElement
public class Cidade implements Serializable, SampleEntity{
	private static final long serialVersionUID = -4278733423491659229L;

	@Id
	@SequenceGenerator(name = "cidade_seq_gen", sequenceName = "cidade_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "cidade_seq_gen", strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Size(max = 60)
	@Column(name = "nome", length = 60)
	private String nome;

	@ManyToOne
 	@JoinColumn(name = "id_estado")
	private Estado estado;

	public Cidade(){
		super();
	}
	
	public Cidade(String nome, Estado estado){
		super();
		this.nome = nome;
		this.estado = estado;
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
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	
	
	

}

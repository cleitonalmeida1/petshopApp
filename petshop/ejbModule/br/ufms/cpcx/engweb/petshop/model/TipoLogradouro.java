package br.ufms.cpcx.engweb.petshop.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.istack.internal.NotNull;

@Entity
@Table(name = "tb_tipo_logradouro")
@XmlRootElement
public class TipoLogradouro implements Serializable {

	private static final long serialVersionUID = 2254288214457494996L;
	@Id
	@SequenceGenerator(name = "tipo_logradouro_seq_gen", sequenceName = "tipo_logradouro_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "tipo_logradouro_seq_gen", strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Size(max = 60)
	@NotNull
	@Column(name = "nome", length = 60)
	private String nome;
	
	private String abreviacao;

	public TipoLogradouro(){
		
	}
	
	public TipoLogradouro(Long id){
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

	public String getAbreviacao() {
		return abreviacao;
	}

	public void setAbreviacao(String abreviacao) {
		this.abreviacao = abreviacao;
	}
}
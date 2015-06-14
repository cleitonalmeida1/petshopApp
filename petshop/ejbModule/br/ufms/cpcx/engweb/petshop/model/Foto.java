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
@Table(name = "tb_foto")
public class Foto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3451228504020963877L;
	@Id
	@SequenceGenerator(name="foto_seq_gen", sequenceName="foto_seq", initialValue=1, allocationSize=1)
	@GeneratedValue(generator="foto_seq_gen", strategy=GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private Long id;
	private String nome;
	private String tipo;
	private byte[] content;
	
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
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}

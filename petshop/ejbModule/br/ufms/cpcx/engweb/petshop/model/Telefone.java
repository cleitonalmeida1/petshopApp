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

@Entity
@Table(name = "tb_telefone")
public class Telefone implements Serializable{


	private static final long serialVersionUID = 59336573134901113L;
	@Id
	@SequenceGenerator(name = "telefone_seq_gen", sequenceName = "telefone_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "telefone_seq_gen", strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Size(max = 30)
    @Column(name = "numtelefone", length = 30)
    private String numero;
	
	public Telefone(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	

}

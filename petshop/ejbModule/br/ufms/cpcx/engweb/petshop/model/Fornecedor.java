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

@Entity
@Table(name = "tb_fornecedor")
public class Fornecedor implements Serializable {

	private static final long serialVersionUID = 199493715310966703L;

	@Id
	@SequenceGenerator(name = "fornecedor_seq_gen", sequenceName = "fornecedor_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "fornecedor_seq_gen", strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private Long id;

	@Size(max = 100)
	@Column(name = "nome", length = 100)
	private String nome;

	@Size(max = 15)
	@Column(name = "cnpj", length = 15)
	private String cnpj;

	@Size(max = 15)
	@Column(name = "ie", length = 15)
	private String ie;

	@Size(max = 40)
	@Column(name = "email", length = 40)
	private String email;

	@Size(max = 15)
	@Column(name = "telefone", length = 15)
	private String telefone;

	@OneToOne(optional = false)
	private Endereco Endereco;

	public Fornecedor() {

	}

	public Fornecedor(Long id) {
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return Endereco;
	}

	public void setEndereco(Endereco endereco) {
		Endereco = endereco;
	}
}

package br.ufms.cpcx.engweb.petshop.model;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import br.ufms.cpcx.engweb.petshop.model.enuns.TipoPessoaEnum;

@Entity
@Table(name = "tb_cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = -3052883518527417260L;

	@Id
	@SequenceGenerator(name = "cliente_seq_gen", sequenceName = "cliente_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "cliente_seq_gen", strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private Long id;

	@Size(max = 100)
	@Column(name = "nome_razaosocial", length = 100)
	private String nomeRazaosocial;

	@Size(max = 15)
	@Column(name = "cpf_cnpj", length = 15)
	private String cpfCnpj;

	@Size(max = 15)
	@Column(name = "rg_ie", length = 15)
	private String rgIe;

	@Size(max = 40)
	@Column(name = "email", length = 40)
	private String email;

	
	@Column(name = "datanascimento")
	@Temporal(TemporalType.DATE)
	private Date datanascimento;

	@Size(max = 1)
	@Column(name = "sexo", length = 1)
	private String sexo;

	// telefone
	
	@OneToOne
	private Telefone telefone;
	// endere�o
	
	@OneToOne
	private Endereco endereco;
	// tipo pessoa
	
	@Enumerated(EnumType.STRING)
	private TipoPessoaEnum tipoPessoa;

	public Cliente() {

	}

	public Cliente(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeRazaosocial() {
		return nomeRazaosocial;
	}

	public void setNomeRazaosocial(String nomeRazaosocial) {
		this.nomeRazaosocial = nomeRazaosocial;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getRgIe() {
		return rgIe;
	}

	public void setRgIe(String rgIe) {
		this.rgIe = rgIe;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public TipoPessoaEnum getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoaEnum tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

}

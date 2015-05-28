package br.ufms.cpcx.engweb.petshop.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_funcionario")
public class Funcionario implements Serializable {

	private static final long serialVersionUID = 6528855611227720330L;

	@Id
	@SequenceGenerator(name = "funcionario_seq_gen", sequenceName = "funcionario_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "funcionario_seq_gen", strategy = GenerationType.SEQUENCE)
	
	private Long id;
    private String nome;
    private String cpf;
    private String rg;
    private String email;
    private String cargo;
    private double salario;
    
    @NotNull
    private Date datanascimento;
    private String sexo;
    
    //telefone
	@OneToOne(optional = false)
	private Telefone Telefone;
    //endereço
    @OneToOne(optional = false)
    private Endereco Endereco;
   

    public Funcionario() {
    	
    }

    public Funcionario(Long id) {
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
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

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Telefone getTelefone() {
		return Telefone;
	}

	public void setTelefone(Telefone telefone) {
		Telefone = telefone;
	}

	public Endereco getEndereco() {
		return Endereco;
	}

	public void setEndereco(Endereco endereco) {
		Endereco = endereco;
	}

	
}
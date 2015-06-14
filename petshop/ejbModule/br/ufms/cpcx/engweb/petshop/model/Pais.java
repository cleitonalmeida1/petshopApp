package br.ufms.cpcx.engweb.petshop.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import br.ufms.cpcx.engweb.petshop.model.interfaces.SampleEntity;

@Entity
@Table(name = "tb_pais")
public class Pais implements Serializable, SampleEntity {
    
	private static final long serialVersionUID = 5125820883370721298L;
	@Id
	@SequenceGenerator(name = "pais_seq_gen", sequenceName = "pais_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "pais_seq_gen", strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private Long id;
	@Size(max = 50)
    private String nome;
	@Transient //não mapear este atributo - apenas como regra de negócio
	private final int tamMaxSigla = 3;
    @Size(max = tamMaxSigla)
    private String sigla;

    public Pais() {
    	super();
    }
    
    public Pais(String nome, String sigla) {
    	super();
    	this.nome = nome;
    	this.sigla = sigla;
    }

    public Pais(Long id) {
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

    public int getTamMaxSigla() {
		return tamMaxSigla;
	}
    
	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
		result = prime * result + tamMaxSigla;
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
		Pais other = (Pais) obj;
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
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		if (tamMaxSigla != other.tamMaxSigla)
			return false;
		return true;
	}
}
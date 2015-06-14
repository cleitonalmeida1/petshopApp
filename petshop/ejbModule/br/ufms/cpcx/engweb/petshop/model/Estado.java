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
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import br.ufms.cpcx.engweb.petshop.model.interfaces.SampleEntity;

@Entity
@Table(name = "tb_estado")
public class Estado implements Serializable, SampleEntity{
    
	private static final long serialVersionUID = 3223383939866070410L;
	@Id
	@SequenceGenerator(name = "estado_seq_gen", sequenceName = "estado_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "estado_seq_gen", strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;
    private String nome;
    @Transient //não mapear este atributo - apenas como regra de negócio
	private final int tamMaxSigla = 2;
    @Size(max = tamMaxSigla)
    private String uf;
    
    // estado
 	
 	@ManyToOne
 	@JoinColumn(name = "id_pais")
 	private Pais pais;

	public Estado() {
		super();
    }
	
	public Estado(String nome, String uf, Pais pais) {
		super();
		this.nome = nome;
		this.uf = uf;
		this.pais = pais;
    }

    public Estado(Long id) {
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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
    
    public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		result = prime * result + tamMaxSigla;
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
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
		Estado other = (Estado) obj;
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
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		if (tamMaxSigla != other.tamMaxSigla)
			return false;
		if (uf == null) {
			if (other.uf != null)
				return false;
		} else if (!uf.equals(other.uf))
			return false;
		return true;
	}
	
	
}

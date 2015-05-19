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
@Table(name = "tb_estado")
public class Estado implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 3223383939866070410L;
	@Id
	@SequenceGenerator(name = "estado_seq_gen", sequenceName = "estado_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "estado_seq_gen", strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private Long id;
	
	
    @Size(max = 50)
    @Column(name = "nome", length = 50)
    private String nome;
    
    @Size(max = 2)
    @Column(name = "uf", length = 2)
    private String uf;
   

    public Estado() {
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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

   

    
    
}

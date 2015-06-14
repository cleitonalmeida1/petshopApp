package br.ufms.cpcx.engweb.petshop.mb.cadastro;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ufms.cpcx.engweb.petshop.biz.CadastroFuncionario;
import br.ufms.cpcx.engweb.petshop.model.Funcionario;

@ManagedBean(name="listagemFuncionariosMB")
@ViewScoped
public class ListagemFuncionariosMB {
	private List<Funcionario> funcionarios;
	private boolean mostrarTabela = true;
	private Long idFuncionario;
//
	@EJB
	private CadastroFuncionario cadastroFuncionario;
	
	@PostConstruct
	public void init() {
		funcionarios = cadastroFuncionario.listarFuncionarios();
	}
	
	public String editar(Long idFuncionarios){
		return "cadastroFuncionario?faces-redirect=true&id="+idFuncionarios.toString();
	}

	public void remover(Funcionario funcionario){
		cadastroFuncionario.removerFuncionario(funcionario);
		funcionarios = cadastroFuncionario.listarFuncionarios();
		mostrarTabela = true;
		if(funcionarios.size()==0){
			mostrarTabela = false;
		}
	}

	public boolean isMostrarTabela() {
		return mostrarTabela;
	}

	public void setMostrarTabela(boolean mostrarTabela) {
		this.mostrarTabela = mostrarTabela;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Long getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
}

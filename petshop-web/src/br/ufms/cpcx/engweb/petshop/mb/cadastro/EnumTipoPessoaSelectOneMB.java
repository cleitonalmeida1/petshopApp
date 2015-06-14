package br.ufms.cpcx.engweb.petshop.mb.cadastro;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import br.ufms.cpcx.engweb.petshop.model.enuns.TipoPessoaEnum;

@Named("enumTipoPessoaSelectOneMB")
@ConversationScoped
public class EnumTipoPessoaSelectOneMB implements Serializable {

	private static final long serialVersionUID = 4337920482566153057L;

	private TipoPessoaEnum tipoPessoaSelecionado;

	public TipoPessoaEnum[] getTiposDePessoas() {
		return TipoPessoaEnum.values();
	}

	public TipoPessoaEnum getTipoPessoaSelecionado() {
		return tipoPessoaSelecionado;
	}

	public void setTipoPessoaSelecionado(TipoPessoaEnum tipoPessoaSelecionado) {
		this.tipoPessoaSelecionado = tipoPessoaSelecionado;
	}

}
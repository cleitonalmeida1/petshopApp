package br.ufms.cpcx.engweb.petshop.mb.cadastro;

import java.io.Serializable;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import br.ufms.cpcx.engweb.petshop.model.enuns.TipoEnderecoEnum;

@Named("enumTipoEnderecoSelectOneMB")
@ConversationScoped
public class EnumTipoEnderecoSelectOneMB implements Serializable {

	private static final long serialVersionUID = 8371032739440471847L;
	private TipoEnderecoEnum tipoEnderecoSelecionado;

	public TipoEnderecoEnum[] getTiposDeEnderecos() {
		return TipoEnderecoEnum.values();
	}

	public TipoEnderecoEnum getTipoEnderecoSelecionado() {
		return tipoEnderecoSelecionado;
	}

	public void setTipoEnderecoSelecionado(
			TipoEnderecoEnum tipoEnderecoSelecionado) {
		this.tipoEnderecoSelecionado = tipoEnderecoSelecionado;
	}
}
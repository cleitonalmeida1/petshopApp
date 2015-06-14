package br.ufms.cpcx.engweb.petshop.model.enuns;

public enum TipoEnderecoEnum {
	RESIDENCIAL("Residencial"), COMERCIAL("Comercial");

	private String rotulo;

	private TipoEnderecoEnum(String rotulo) {
		this.rotulo = rotulo;
	}

	public String getRotulo() {
		return rotulo;
	}
}
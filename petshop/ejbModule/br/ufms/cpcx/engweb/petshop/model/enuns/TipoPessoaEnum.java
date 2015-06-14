package br.ufms.cpcx.engweb.petshop.model.enuns;

public enum TipoPessoaEnum {
	FISICA("Física"), JURIDICA("Jurídica");

	private String rotulo;

	private TipoPessoaEnum(String rotulo) {
		this.rotulo = rotulo;
	}

	public String getRotulo() {
		return rotulo;
	}
}
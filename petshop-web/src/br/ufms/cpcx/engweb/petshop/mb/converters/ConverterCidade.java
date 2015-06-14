package br.ufms.cpcx.engweb.petshop.mb.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ufms.cpcx.engweb.petshop.biz.CadastroCidade;
import br.ufms.cpcx.engweb.petshop.model.Cidade;

@FacesConverter(value = "converterCidade", forClass = Cidade.class)
public class ConverterCidade implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.equals("")) {
			CadastroCidade cadastroCidade = new CadastroCidade();
			return cadastroCidade.buscarCidadePorId(Long.valueOf(value));
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof Cidade) {
			Cidade cidade = (Cidade) value;
			return String.valueOf(cidade.getId());
		}
		return "";
	}

}

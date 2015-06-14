package br.ufms.cpcx.engweb.petshop.mb.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ufms.cpcx.engweb.petshop.biz.CadastroPais;
import br.ufms.cpcx.engweb.petshop.model.Pais;

@FacesConverter(value = "converterPais", forClass = Pais.class)
public class ConverterPais implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.equals("")) {
			CadastroPais cadastroPais = new CadastroPais();
			System.out.println("Passou");
			Pais pais = cadastroPais.buscarPaisPorId(Long.valueOf(value));
			return pais;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof Pais) {
			Pais pais = (Pais) value;
			return String.valueOf(pais.getId());
		}
		return "";
	}

}

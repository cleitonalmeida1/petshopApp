package br.ufms.cpcx.engweb.petshop.mb.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ufms.cpcx.engweb.petshop.biz.CadastroEstado;
import br.ufms.cpcx.engweb.petshop.model.Estado;

@FacesConverter(value = "converterEstado", forClass = Estado.class)
public class ConverterEstado implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.equals("")) {
			CadastroEstado cadastroEstado = new CadastroEstado();
			return cadastroEstado.buscarEstadoPorId(Long.valueOf(value));
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof Estado) {
			Estado estado = (Estado) value;
			return String.valueOf(estado.getId());
		}
		return "";
	}

}

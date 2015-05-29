package br.ufms.cpcx.engweb.petshop.mb.cadastro;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.ufms.cpcx.engweb.petshop.biz.CadastroFoto;
import br.ufms.cpcx.engweb.petshop.model.Foto;

@ManagedBean
@SessionScoped
public class ImagemMB {
	@EJB
	private CadastroFoto cadastroFoto;

	public StreamedContent getImage() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {
			String fotoId = context.getExternalContext().getRequestParameterMap().get("fotoId");
			if (fotoId.equals("")) {
				return null;
			}
			Foto f = cadastroFoto.buscarFotoPorId(new Long(fotoId));
			return new DefaultStreamedContent(new ByteArrayInputStream(f.getContent()), f.getTipo());
		}
	}
}

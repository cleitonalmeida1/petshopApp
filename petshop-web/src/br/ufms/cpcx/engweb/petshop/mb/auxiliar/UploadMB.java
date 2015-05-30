package br.ufms.cpcx.engweb.petshop.mb.auxiliar;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@SessionScoped
public class UploadMB implements Serializable {
	private static final long serialVersionUID = 1616539422376403150L;
	private UploadedFile arquivo;
	
	public void handleFileUpload(FileUploadEvent event) {
        this.arquivo = event.getFile();
    }
	
	public UploadedFile getArquivo() {
		UploadedFile arquivoAux = arquivo;
		arquivo = null;
		return arquivoAux;
	}
	public void setArquivo(UploadedFile arquivo) {
		this.arquivo = arquivo;
	}
	
	
	
}
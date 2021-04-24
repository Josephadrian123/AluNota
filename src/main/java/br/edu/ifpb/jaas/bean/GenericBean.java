package br.edu.ifpb.jaas.bean;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class GenericBean {
	
	protected void addMessage(String mensagem, Severity severidade) {
		FacesMessage fm = new FacesMessage(mensagem);
		fm.setSeverity(severidade);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, fm);
	}
	
	protected void addInfoMessage(String mensagem ) {
		this.addMessage(mensagem, FacesMessage.SEVERITY_INFO);
	}
	
	protected void addErrorMessage(String mensagem ) {
		this.addMessage(mensagem, FacesMessage.SEVERITY_ERROR);
	}

}
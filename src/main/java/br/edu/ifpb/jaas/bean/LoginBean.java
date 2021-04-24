package br.edu.ifpb.jaas.bean;

import java.io.Serializable;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.jaas.controller.UsuarioController;
import br.edu.ifpb.jaas.model.Usuario;

@Named
@ViewScoped
public class LoginBean extends GenericBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
    
    @Inject
	private Usuario usuario;
    
    @Inject
	private UsuarioController controller;
    
    
    
   
   @PostConstruct
    public void init() {
		Usuario u = new Usuario();
		
		u.setNome("admin");
		u.setEmail("admin@example.com");
		u.setSenha("123");
		
		if(controller.findUser(u) == null) {
		controller.insert(u);
		}
	}

	public String logIn() {
        
        if (controller.findUser(usuario) == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário ou Senha Inválidos", "Login Inválido"));
       
            return null;
            
        } else {
        	
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            if (session != null) {
            	session.setAttribute("usuarioLogado", usuario);
                
            }
            return "/index?faces-redirect=true";
        }
    }

    public String logOff() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.invalidate();
        return "/login?faces-redirect=true";
    }
    
    

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
}

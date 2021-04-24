package br.edu.ifpb.jaas.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.EnumSet;


import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.jaas.controller.AlunoController;
import br.edu.ifpb.jaas.model.Aluno;
import br.edu.ifpb.jaas.model.Situacao;

@Named(value = "cadAlunoBean")
@SessionScoped
public class CadastroBean extends GenericBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Aluno aluno;
	
	
	@Inject
	private AlunoController controller;
	
	public String cadastrar() {
		// Usa o dao para inserir o aluno
		controller.insert(aluno);
		
		// Limpa objeto do formulário
		aluno = new Aluno();
		
		// Mensagem para a interface
		this.addInfoMessage("Aluno cadastrado com sucesso!");
		
		// Retorna para mesma página
		return null;
	}
	
	public String editarNotas(Aluno aluno) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
        	
        	session.setAttribute("aluno", aluno);
            
        }
        
		return "/alunos/editarNotas?faces-redirect=true";
	}

	public String salvarNotas(Aluno a) {
		int contador = 0;
		
		if(aluno.getNotaUm() != null) {
			a.setNotaUm(aluno.getNotaUm());
		}else contador +=1;
		
		if(aluno.getNotaDois() != null) {
			a.setNotaDois(aluno.getNotaDois());
		}else contador +=1;
		
		if(aluno.getNotaTres() != null) {
			a.setNotaTres(aluno.getNotaTres());
		}else contador +=1;
		
		if(aluno.getNotaFinal() != null) {		
			if(a.getNotaUm() != null && a.getNotaDois() != null && a.getNotaTres() != null && a.getFaltas() != null) {
				if(a.mediaNotas().compareTo(new BigDecimal(40.00)) > 0 && a.mediaNotas().compareTo(new BigDecimal(70.00)) < 0 && a.getFaltas() <= 25) {
					a.setNotaFinal(aluno.getNotaFinal());	
				}else {
					this.addInfoMessage("Aluno Aprovado ou inelegível a prova final!");
					return null;
				}
			}
			
			
		}else contador +=1;
		
		if(aluno.getFaltas() != null) {
			a.setFaltas(aluno.getFaltas());
		}else contador +=1;
		
		if(contador == 5) {
			this.addInfoMessage("Nenhum campo preenchido!");
			return null;
		}else {
		controller.update(a);
		
		aluno = new Aluno();
		
		this.addInfoMessage("Notas cadastradas com sucesso!");
		}
		
		return null;
	}
	
	public String formEditar(Aluno aluno) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
        	
        	session.setAttribute("aluno", aluno);
            
        }
        
		return "/alunos/editarAluno?faces-redirect=true";
	}
	
	public String editar(Aluno a) {
		int contador = 0;
		
		if(aluno.getNome() != null) {
			a.setNome(aluno.getNome());
		}else contador += 1;
		
		if(aluno.getDataNasc() != null) {
			a.setDataNasc(aluno.getDataNasc());
		}else contador += 1;
		
		if(contador == 2) {
			this.addInfoMessage("Nenhum campo preenchido!");
			return null;
		}else {
		controller.update(a);
		
		aluno = new Aluno();
		
		this.addInfoMessage("Aluno editado com sucesso!");
		}
		
		return null;
	}
	
	public EnumSet<Situacao> getAlunosItens(){
		return EnumSet.allOf(Situacao.class);
		
		//retorna as possíveis situações do aluno
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

}

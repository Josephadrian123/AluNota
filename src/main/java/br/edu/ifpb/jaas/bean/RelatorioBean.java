package br.edu.ifpb.jaas.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.jaas.controller.AlunoController;
import br.edu.ifpb.jaas.model.Aluno;

@Named(value = "consAlunoBean")
@ViewScoped
public class RelatorioBean extends GenericBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Aluno> alunos;
	
	private Map<Integer, Boolean> checked = new HashMap<Integer, Boolean>();
	
	@Inject 
	private AlunoController controller;

	@PostConstruct
	public void init() {
		alunos = controller.findAll();
	}

	public String excluir(Aluno aluno) {
		controller.excluir(aluno);
		this.addInfoMessage("Aluno excluído com sucesso!");
		return "/alunos/relatorio?faces-redirect=true";
	}
	
	
	public String excluirSelecionados() {
		Aluno a = null;
		for (Integer id : checked.keySet()) {
			if (checked.get(id)) {
				a = controller.find(id);
				controller.excluir(a);
			}
		}
		alunos = controller.findAll();
		checked.clear();
		this.addInfoMessage("Alunos selecionados excluídos com sucesso!");
		return "/alunos/relatorio?faces-redirect=true";
	}
	
	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Map<Integer, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(Map<Integer, Boolean> checked) {
		this.checked = checked;
	}

}

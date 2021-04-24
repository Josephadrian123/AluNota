package br.edu.ifpb.jaas.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.jaas.dao.AlunoDAO;
import br.edu.ifpb.jaas.dao.Transactional;
import br.edu.ifpb.jaas.model.Aluno;

public class AlunoController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private AlunoDAO alunoDAO;
	
	@Transactional
	public void excluir(Aluno aluno) {
		alunoDAO.delete(aluno);
		
	}
	
	public List<Aluno> findAll() {
		return alunoDAO.findAll();
	}
	
	public Aluno find(Integer id) {
		return alunoDAO.find(id);
	}
	
	@Transactional
	public Aluno insert(Aluno aluno) {
		return alunoDAO.insert(aluno);
	}
	
	@Transactional
	public Aluno update(Aluno aluno) {
		return alunoDAO.update(aluno);
	}
}
package br.edu.ifpb.jaas.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.edu.ifpb.jaas.dao.UsuarioDAO;
import br.edu.ifpb.jaas.dao.Transactional;
import br.edu.ifpb.jaas.model.Usuario;

public class UsuarioController implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarioDAO;
	
	public void excluir(Usuario usuario) {
		usuarioDAO.delete(usuario);
		
	}
	
	public List<Usuario> findAll() {
		return usuarioDAO.findAll();
	}
	
	public Usuario find(Integer id) {
		return usuarioDAO.find(id);
	}
	
	public Usuario findUser(Usuario u) {
		List<Usuario> usuarios = usuarioDAO.findAll();
		
		for(Usuario usuario : usuarios) {
			if(u.getNome().equals(usuario.getNome()) && u.getSenha().equals(usuario.getSenha())) {
				
				return u;
			}
		}
		 
		return null;
	}
	
	@Transactional
	public Usuario insert(Usuario usuario) {
		return usuarioDAO.insert(usuario);
	}

}

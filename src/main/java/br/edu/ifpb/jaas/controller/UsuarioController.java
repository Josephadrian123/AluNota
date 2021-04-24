package br.edu.ifpb.jaas.controller;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
			if(u.getNome().equals(usuario.getNome()) && convertStringToMd5(u.getSenha()).equals(usuario.getSenha())) {
				
				return u;
			}
		}
		 
		return null;
	}
	
	@Transactional
	public Usuario insert(Usuario usuario) {
		usuario.setSenha(convertStringToMd5(usuario.getSenha()));
		return usuarioDAO.insert(usuario);
	}
	
	public String convertStringToMd5(String valor) {
  	  MessageDigest mDigest;
  	  try {
  	      //Instancia o nosso HASH MD5
  	      mDigest = MessageDigest.getInstance("MD5");

  	      //Converte a String valor para um array de bytes em MD5
  	      byte[] valorMD5 = mDigest.digest(valor.getBytes("UTF-8"));

  	      //Converte os bytes para hexadecimal, assim podemos salvar
  	      //no banco para posterior comparação se senhas
  	      StringBuffer sb = new StringBuffer();
  	      for (byte b : valorMD5){
  	             sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1,3));
  	      }

  	      return sb.toString();

  	  } catch (NoSuchAlgorithmException e) {
  	      // TODO Auto-generated catch block
  	      e.printStackTrace();
  	      return null;
  	  } catch (UnsupportedEncodingException e) {
  	      // TODO Auto-generated catch block
  	      e.printStackTrace();
  	      return null;
  	  }
  	}

}

package com.unidesc.localiza.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.unidesc.localiza.entity.Usuario;
import com.unidesc.localiza.exceptions.UsuarioDuplicadoException;
import com.unidesc.localiza.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	public List<Usuario> buscartodosUsuario(){
		return usuarioRepository.findAll();
		
	}
	
	public Usuario buscarLogin(String login) {
		return usuarioRepository.buscarLogin(login);
	}
		
	public Usuario salvarUsuario( Usuario usuario) throws UsuarioDuplicadoException {
		Usuario usuarioEncontrado = usuarioRepository.buscarLogin(usuario.getLogin());
		if(usuarioEncontrado != null) {
			throw new UsuarioDuplicadoException("Usuario Duplicado!  ID: " + usuarioEncontrado.getIdUsuario());
		}
		return usuarioRepository.save(usuario);
	}
	
	public Usuario atualizarUsuario( Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public void deletarUsuario(Long idUsuario) {
		usuarioRepository.deleteById(idUsuario);
	}
}

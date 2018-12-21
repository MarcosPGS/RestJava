package com.unidesc.localiza.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.unidesc.localiza.entity.Usuario;
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
		
	public Usuario salvarUsuario(@RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public Usuario atualizarUsuario(@RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public void deletarUsuario(@RequestBody Usuario usuario) {
		usuarioRepository.delete(usuario);
	}
}

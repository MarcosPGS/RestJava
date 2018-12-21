package com.unidesc.localiza.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unidesc.localiza.entity.Usuario;
import com.unidesc.localiza.negocio.service.UsuarioService;
import com.unidesc.localiza.repository.UsuarioRepository;

@CrossOrigin
@RestController
@RequestMapping(value="/api")
public class UsuarioResource {
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/usuario/login/{login}")
	public Usuario buscaLogin(@PathVariable String login) {
		return usuarioService.buscarLogin(login);
	}
	
	@GetMapping("/usuario")
	public List<Usuario> buscatodos(){
		return usuarioService.buscartodosUsuario();
	}
	
	@PostMapping("/usuario")
	public Usuario salvaUsuario(@RequestBody Usuario usuario) {
		return usuarioService.salvarUsuario(usuario);
	}

	@PutMapping("/usuario")
	public Usuario atualizaUsuario(@RequestBody Usuario usuario) {
		return usuarioService.atualizarUsuario(usuario);
	}
	
	@DeleteMapping("/usuario")
	public void deletaUsuario(@RequestBody Usuario usuario) {
		usuarioService.deletarUsuario(usuario);
	}

}

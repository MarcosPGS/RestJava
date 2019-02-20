package com.unidesc.localiza.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.unidesc.localiza.exceptions.UsuarioDuplicadoException;
import com.unidesc.localiza.negocio.service.UsuarioService;
import com.unidesc.localiza.repository.UsuarioRepository;

@CrossOrigin
@RestController
@RequestMapping(value="/usuario")
public class UsuarioResource {
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/{login}")
	public Usuario buscaLogin(@PathVariable String login) {
		return usuarioService.buscarLogin(login);
	}
	
	@GetMapping()
	public List<Usuario> buscatodos(){
		return usuarioService.buscartodosUsuario();
	}
	
	@PostMapping()
	public ResponseEntity<Object> salvaUsuario(@RequestBody Usuario usuario) {
		try {
			return ResponseEntity.ok().body(usuarioService.salvarUsuario(usuario));
		} catch (UsuarioDuplicadoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}

	@PutMapping()
	public Usuario atualizaUsuario(@RequestBody Usuario usuario) {
		return usuarioService.atualizarUsuario(usuario);
	}
	
	@DeleteMapping("/{idUsuario}")
	public void deletaUsuario(@PathVariable Long idUsuario) {
		usuarioService.deletarUsuario(idUsuario);
	}

}

package com.unidesc.localiza.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unidesc.localiza.entity.Usuario;
import com.unidesc.localiza.repository.UsuarioRepository;

@CrossOrigin
@RestController
@RequestMapping(value="/api")
public class UsuarioResource {
	
	@Autowired
	UsuarioRepository usuarioRepository;//TODO: TROCAR PARA USUARIOSERVICE
	
	@GetMapping("/usuario/login/{login}")
	public Usuario buscaLogin(@PathVariable String login) {
		return usuarioRepository.buscarLogin(login);
	}

}

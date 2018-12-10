package com.unidesc.localiza.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unidesc.localiza.entity.Administrador;
import com.unidesc.localiza.negocio.service.AdministradorService;

@CrossOrigin
@RestController
@RequestMapping(value="/api")
public class AdministradorResource {

	@Autowired
	AdministradorService administradorService;
	
	@GetMapping("/administrador")
	public List<Administrador>buscaTodos(){
		return administradorService.buscarTodosAdministador();
	}
	
	@PostMapping("/administrador")
	public Administrador criaAdmin(@RequestBody Administrador administrador) {
		return administradorService.criarAdministrador(administrador);
		}
	
	@PutMapping("/administrador")
	public Administrador atualizaAdmin(@RequestBody Administrador administrador) {
		return administradorService.atualizarAdministrador(administrador);
		}
	
	@DeleteMapping("/administrador")
	public void deletaAdmin(@RequestBody Administrador administrador) {
		administradorService.deletarAdministrador(administrador);
		}
}

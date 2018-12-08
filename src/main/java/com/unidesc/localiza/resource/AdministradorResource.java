package com.unidesc.localiza.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
}

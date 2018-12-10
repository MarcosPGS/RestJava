package com.unidesc.localiza.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.unidesc.localiza.entity.Administrador;
import com.unidesc.localiza.entity.Turno;
import com.unidesc.localiza.repository.AdministradorRepository;

@Service
public class AdministradorService {

	@Autowired
	AdministradorRepository administradorRepository;
	
	public List<Administrador> buscarTodosAdministador(){
		return administradorRepository.findAll();
	}
	
	public Administrador criarAdministrador(@RequestBody Administrador administrador) {
		return administradorRepository.save(administrador);
	}
	
	public Administrador atualizarAdministrador(@RequestBody Administrador administrador) {
		return administradorRepository.save(administrador);
	}
	
	public void deletarAdministrador(@RequestBody Administrador administrador) {
		administradorRepository.delete(administrador);
	}
	
	
}

package com.unidesc.localiza.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unidesc.localiza.entity.Administrador;
import com.unidesc.localiza.repository.AdministradorRepository;

@Service
public class AdministradorService {

	@Autowired
	AdministradorRepository administradorRepository;
	
	public List<Administrador> buscarTodosAdministador(){
		return administradorRepository.findAll();
	}
	
}

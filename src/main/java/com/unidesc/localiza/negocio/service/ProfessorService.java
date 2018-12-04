package com.unidesc.localiza.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unidesc.localiza.entity.Professor;
import com.unidesc.localiza.repository.ProfessorRepository;

@Service
public class ProfessorService {

	@Autowired
	ProfessorRepository professorRepository;
	
	public Professor buscarPorNome(String nome) {
		return professorRepository.buscarNome(nome);
	}
	
	
	public List<Professor> buscarTodos(){
		return professorRepository.findAll();
	}
	
	
	
}

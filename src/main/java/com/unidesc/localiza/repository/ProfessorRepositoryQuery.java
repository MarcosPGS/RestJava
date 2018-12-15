package com.unidesc.localiza.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.unidesc.localiza.entity.Professor;

public interface ProfessorRepositoryQuery {
	
	public List<Professor> buscarNome(String nome);
	
	
	public Page<Professor> buscarNomeP(String nome, Pageable pageable);

}

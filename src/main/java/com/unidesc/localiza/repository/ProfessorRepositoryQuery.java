package com.unidesc.localiza.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.unidesc.localiza.entity.Professor;
import com.unidesc.localiza.negocio.to.FiltroProfessor;

public interface ProfessorRepositoryQuery {
	
	public List<Professor> buscarNome(String nome);
	public Page<Professor> buscarNomePaginado(String nome, Pageable pageable);
	public Professor buscarPorMatricula(String matricula);
	public List<Professor> pesquisar(FiltroProfessor f);
	
	
	
}

package com.unidesc.localiza.repository;

import java.util.List;

import com.unidesc.localiza.entity.Curso;

public interface CursoRepositoryQuery {
	
	public List<Curso> buscarPorNomeCurso(String nome);
	
}

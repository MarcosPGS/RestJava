package com.unidesc.localiza.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.unidesc.localiza.entity.Disciplina;

public interface DisciplinaRepositoryQuery {

	public List<Disciplina> buscarPorNomeDisciplina(String nome);
	
	public Page<Disciplina> buscarNomePagina(String nome, Pageable pageable);
}

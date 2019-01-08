package com.unidesc.localiza.repository;

import java.util.List;

import com.unidesc.localiza.entity.DiaSemana;

public interface DiaSemanaRepositoryQuery {
 
	public List<DiaSemana> buscarPorDiaSemana(String descricao);
	public DiaSemana buscarPorDiaSemanaUnico(String descricao);
	
}

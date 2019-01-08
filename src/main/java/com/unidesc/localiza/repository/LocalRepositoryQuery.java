package com.unidesc.localiza.repository;

import java.util.List;

import com.unidesc.localiza.entity.Local;

public interface LocalRepositoryQuery {

	public List<Local> buscaPorBloco(String bloco);
	
	public Local buscarPorBlocoUnico (String bloco);
	
	public Local buscarPorSalaUnico(String sala);
}

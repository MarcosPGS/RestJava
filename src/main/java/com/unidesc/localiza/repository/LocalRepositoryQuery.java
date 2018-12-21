package com.unidesc.localiza.repository;

import java.util.List;

import com.unidesc.localiza.entity.Local;

public interface LocalRepositoryQuery {

	public List<Local> buscaPorBloco(String bloco);
}

package com.unidesc.localiza.repository;

import com.unidesc.localiza.entity.Sala;

public interface SalaRepositoryQuery {
	public Sala buscarPorNome(String descricao);
}

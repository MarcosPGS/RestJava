package com.unidesc.localiza.repository;

import com.unidesc.localiza.entity.Bloco;

public interface BlocoRepositoryQuery {

	public Bloco buscarPorNome(String descricao);
}

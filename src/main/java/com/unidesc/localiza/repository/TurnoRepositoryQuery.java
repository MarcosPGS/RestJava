package com.unidesc.localiza.repository;

import com.unidesc.localiza.entity.Turno;

public interface TurnoRepositoryQuery {

	public Turno buscarPorTurno(String descricao);
}

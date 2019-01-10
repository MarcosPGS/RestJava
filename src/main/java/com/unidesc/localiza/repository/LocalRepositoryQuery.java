package com.unidesc.localiza.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.unidesc.localiza.entity.Local;

public interface LocalRepositoryQuery {

	public List<Local> buscaPorBloco(String bloco);

	
	public Local buscarPorLocalUnico(String bloco, String sala);
}

package com.unidesc.localiza.repository;

import com.unidesc.localiza.entity.Usuario;

public interface UsuarioRepositoryQuery {
	
	public Usuario buscarLogin(String login);

}

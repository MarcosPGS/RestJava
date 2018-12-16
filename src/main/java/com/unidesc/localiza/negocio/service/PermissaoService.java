package com.unidesc.localiza.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.unidesc.localiza.entity.Permissao;
import com.unidesc.localiza.repository.PermissaoRepository;

@Service
public class PermissaoService {

	@Autowired
	PermissaoRepository permissaoRepository;
	
	public List<Permissao> buscarTodasPermissao(){
		return permissaoRepository.findAll();
		}
	
	public Permissao criarPermissao(@RequestBody Permissao permissao) {
		return permissaoRepository.save(permissao);
	}
	
	public Permissao atualizarPermissao(@RequestBody Permissao permissao) {
		return permissaoRepository.save(permissao);
	}
	
	public void deletarPermissao(@RequestBody Permissao permissao) {
		permissaoRepository.delete(permissao);
	}
}

package com.unidesc.localiza.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.unidesc.localiza.entity.Permissao;
import com.unidesc.localiza.exceptions.PermissaoDuplicadoException;
import com.unidesc.localiza.repository.PermissaoRepository;

@Service
public class PermissaoService {

	@Autowired
	PermissaoRepository permissaoRepository;
	
	public List<Permissao> buscarTodasPermissao(){
		return permissaoRepository.findAll();
		}
	
	public Permissao salvarPermissao(Permissao permissao) throws PermissaoDuplicadoException {
		Permissao permissaoEncontrada = permissaoRepository.buscarPorPermissaoUnica(permissao.getPermissao());
		if(permissaoEncontrada != null) {
			throw new PermissaoDuplicadoException("Permissao Duplicada!  ID: " + permissaoEncontrada.getIdPermissao());
		}
		return permissaoRepository.save(permissao);
	}
	
	public Permissao atualizarPermissao(Permissao permissao) {
		return permissaoRepository.save(permissao);
	}
	
	public void deletarPermissao(Long idPermissao) {
		permissaoRepository.deleteById(idPermissao);
	}
}

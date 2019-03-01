package com.unidesc.localiza.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unidesc.localiza.entity.Bloco;
import com.unidesc.localiza.exceptions.BlocoDuplicadoException;
import com.unidesc.localiza.repository.BlocoRepository;

@Service
public class BlocoService {
	
	@Autowired
	private BlocoRepository blocoRepository;
	
//	listar todos blocos
	public List<Bloco> listaTodos(){
		return blocoRepository.findAll();
	}
	
//	CRIAR BLOCO
	public Bloco salvarBloco( Bloco bloco) throws BlocoDuplicadoException {
		Bloco blocoEncotrado = blocoRepository.buscarPorNome(bloco.getDescricao());
		if(blocoEncotrado != null) {
			throw new BlocoDuplicadoException("Bloco Duplicado!" + " ID: " + blocoEncotrado.getIdBloco());
		}
		return blocoRepository.save(bloco);
	}
	
//	atualizar BLOCO
	public Bloco atualizarBloco( Bloco bloco) {
		return blocoRepository.save(bloco);
	}
//	deletar BLOCO
	public void deletarBloco( Long idBloco) {
		blocoRepository.deleteById(idBloco);
	}
	
	
	

}

package com.unidesc.localiza.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.unidesc.localiza.entity.Local;
import com.unidesc.localiza.exceptions.LocalDuplicadoException;
import com.unidesc.localiza.repository.LocalRepository;

@Service
public class LocalService {
	
	@Autowired
	LocalRepository localRepository;
	
	public List<Local> buscarPorBloco(String bloco) {
		return localRepository.buscaPorBloco(bloco);
	}
	
	public List<Local> buscarTodosLocais(){
		return localRepository.findAll();
	}
	
	public Local salvarLocal(@RequestBody Local local) throws LocalDuplicadoException {
		
		Local localEncontrado = localRepository.buscarPorBlocoUnico(local.getBloco());
		Local localEncontrado2 = localRepository.buscarPorSalaUnico(local.getSala());
	
		if(localEncontrado != null && localEncontrado2 != null) {
			throw new LocalDuplicadoException("Local Duplicado! -  " +"ID: " + localEncontrado.getIdLocal() + 
					" BLOCO: " + localEncontrado.getBloco() + " SALA: " + localEncontrado2.getSala() );
		}
		
		return localRepository.save(local);
	}
	
	public Local atualizarLocal(@RequestBody Local local) {
		return localRepository.save(local);
	}
	
	public void deletarLocal(@RequestBody Local local) {
		localRepository.delete(local);
	}
		
}

package com.unidesc.localiza.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.unidesc.localiza.entity.Local;
import com.unidesc.localiza.entity.Professor;
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
	
	public Page<Local> buscarLocalPaginado(String bloco, Pageable pageable){
		return localRepository.buscarLocalPaginado(bloco, pageable);
		
	}
	

	
	public Local salvarLocal( Local local) throws LocalDuplicadoException {
		
		Local localEncontrado = localRepository.buscarPorLocalUnico(local.getBloco(), local.getSala());
		
	
		if(localEncontrado != null ) {
			throw new LocalDuplicadoException("Local Duplicado! -  " +"ID: " + localEncontrado.getIdLocal() + 
					" BLOCO: " + localEncontrado.getBloco() + " SALA: " + localEncontrado.getSala() );
		}
		
		return localRepository.save(local);
	}
	
	public Local atualizarLocal( Local local) {
		return localRepository.save(local);
	}
	
	public void deletarLocal( Long idLocal) {
		localRepository.deleteById(idLocal);
	}
		
}

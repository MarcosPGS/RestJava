package com.unidesc.localiza.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unidesc.localiza.entity.Sala;
import com.unidesc.localiza.exceptions.BlocoDuplicadoException;
import com.unidesc.localiza.exceptions.SalaDuplicadaException;
import com.unidesc.localiza.repository.SalaRepository;

@Service
public class SalaService {
	
	@Autowired
	private SalaRepository salaRepository;
	
//	listar todos SALA
	public List<Sala> listaTodos(){
		return salaRepository.findAll();
	}
	
//	CRIAR SALA
	public Sala salvarSala( Sala sala) throws SalaDuplicadaException {
		Sala salaEncotrado = salaRepository.buscarPorNome(sala.getDescricao());
		if(salaEncotrado != null) {
			throw new SalaDuplicadaException("Sala Duplicado!" + " ID: " + salaEncotrado.getIdSala());
		}
		return salaRepository.save(sala);
	}
	
//	atualizar SALA
	public Sala atualizarSala( Sala sala) {
		return salaRepository.save(sala);
	}
//	deletar SALA
	public void deletarSala( Long idSala) {
		salaRepository.deleteById(idSala);
	}
	

}

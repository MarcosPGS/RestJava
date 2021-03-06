package com.unidesc.localiza.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.unidesc.localiza.entity.DiaSemana;
import com.unidesc.localiza.exceptions.DiaSemanaDuplicadoException;
import com.unidesc.localiza.repository.DiaSemanaRepository;

@Service
public class DiaSemanaService {

	@Autowired
	DiaSemanaRepository diaSemanaRepository;
	
	public List<DiaSemana> buscarPorDiaSemana(String descricao){
		return diaSemanaRepository.buscarPorDiaSemana(descricao);
	}
	
	public List<DiaSemana> buscarTodosDias(){
		return diaSemanaRepository.findAll();
	}
	
	public DiaSemana salvarDiaSemana(DiaSemana diaSemana) throws DiaSemanaDuplicadoException {
		DiaSemana diaSemanaEncontrado = diaSemanaRepository.buscarPorDiaSemanaUnico(diaSemana.getDescricao());
		if(diaSemanaEncontrado != null) {
			throw new DiaSemanaDuplicadoException("Dia Da Semana Duplicado! - " + "ID: "+ diaSemanaEncontrado.getIdDiaSemana());
		}
		
		return diaSemanaRepository.save(diaSemana);
	}
	
	public DiaSemana atualizarDiaSemana(DiaSemana diaSemana) {
		return diaSemanaRepository.save(diaSemana);
	}
	public void deletarDiaSemana(Long idDiaSemana) {
		diaSemanaRepository.deleteById(idDiaSemana);
	}
}

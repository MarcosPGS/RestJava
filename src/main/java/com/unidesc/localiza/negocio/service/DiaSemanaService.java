package com.unidesc.localiza.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.unidesc.localiza.entity.DiaSemana;
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
	
	public DiaSemana criarDiaSemana(@RequestBody DiaSemana diaSemana) {
		return diaSemanaRepository.save(diaSemana);
	}
	
	public DiaSemana atualizarDiaSemana(@RequestBody DiaSemana diaSemana) {
		return diaSemanaRepository.save(diaSemana);
	}
	public void deletarDiaSemana(@RequestBody DiaSemana diaSemana) {
		diaSemanaRepository.delete(diaSemana);
	}
}

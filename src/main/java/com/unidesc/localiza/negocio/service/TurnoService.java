package com.unidesc.localiza.negocio.service;

import java.util.List;

import org.omg.CORBA.TRANSACTION_UNAVAILABLE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.unidesc.localiza.entity.Turno;
import com.unidesc.localiza.repository.TurnoRepository;

@Service
public class TurnoService {
	
	@Autowired
	TurnoRepository turnoRepository;
	
	public Turno bucarPorTurno(String descricao) {
		return turnoRepository.buscarPorTurno(descricao);
	}
	
	public List<Turno> buscarTodosTurnos(){
		return turnoRepository.findAll();
	}
	
	public Turno salvarTurno(@RequestBody Turno turno) {
		return turnoRepository.save(turno);
	}
	
	public Turno atualizarTurno(@RequestBody Turno turno) {
		return turnoRepository.save(turno);
	}
	
	public void deletarTurno(@RequestBody Turno turno) {
		turnoRepository.delete(turno);
	}

}

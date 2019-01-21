package com.unidesc.localiza.negocio.service;

import java.util.List;

import org.omg.CORBA.TRANSACTION_UNAVAILABLE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.unidesc.localiza.entity.Turno;
import com.unidesc.localiza.exceptions.TurnoDuplicadoException;
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
	
	public Turno salvarTurno(Turno turno) throws TurnoDuplicadoException {
		Turno turnoEncontrado = turnoRepository.buscarPorTurno(turno.getDescricao());
		if(turnoEncontrado != null) {
			throw new TurnoDuplicadoException("Turno Duplicado - " + "ID: " + turnoEncontrado.getIdTurno());
		}
		
		return turnoRepository.save(turno);
	}
	
	public Turno atualizarTurno(Turno turno) {
		return turnoRepository.save(turno);
	}
	
	public void deletarTurno( Long idTurno) {
		turnoRepository.deleteById(idTurno);
	}

}

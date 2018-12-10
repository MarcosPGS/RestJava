package com.unidesc.localiza.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unidesc.localiza.entity.Turno;
import com.unidesc.localiza.negocio.service.TurnoService;

@CrossOrigin
@RestController
@RequestMapping(value="/api")
public class TurnoResource {
	@Autowired
	TurnoService turnoService;
	
	@GetMapping("/turno")
	public List<Turno> buscaTurno(){
		return turnoService.buscarTodosTurnos();
	}
	
	@PostMapping("/turno")
	public Turno criaTurno(@RequestBody Turno turno) {
		return turnoService.criarTurno(turno);
	}
	
	@PutMapping("/turno")
	public Turno atualizaTurno(@RequestBody Turno turno) {
		return turnoService.atualizarTurno(turno);
	}
	
	@DeleteMapping("/turno")
	public void deletaTurno(@RequestBody Turno turno) {
		turnoService.deletarTurno(turno);
	}
}

package com.unidesc.localiza.resource;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unidesc.localiza.entity.Turno;
import com.unidesc.localiza.exceptions.TurnoDuplicadoException;
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
	
	
	@GetMapping("/turno/descricao/{descricao}")
	public ResponseEntity<Turno> buscaPorTurno(@PathVariable String descricao){
		Turno turnoEncontrado = turnoService.bucarPorTurno(descricao);
		if(turnoEncontrado == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(turnoEncontrado);
		}
		return ResponseEntity.ok().body(turnoEncontrado);
		
	}
	@PostMapping("/turno")
	public ResponseEntity<Object> salvaTurno(@RequestBody Turno turno) {
		try {
			return ResponseEntity.ok().body( turnoService.salvarTurno(turno));
		} catch (TurnoDuplicadoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
	@PutMapping("/turno")
	public Turno atualizaTurno(@RequestBody Turno turno) {
		return turnoService.atualizarTurno(turno);
	}
	
	@DeleteMapping("/turno/{idTurno}")
	public void deletaTurno(@PathVariable Long idTurno) {
		turnoService.deletarTurno(idTurno);
	}
}

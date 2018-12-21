package com.unidesc.localiza.resource;

import java.util.List;
import static org.springframework.http.ResponseEntity.ok;
import org.springframework.beans.factory.annotation.Autowired;
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
			return ok(null);
		}
		return ok(turnoEncontrado);
		
	}
	@PostMapping("/turno")
	public Turno salvaTurno(@RequestBody Turno turno) {
		return turnoService.salvarTurno(turno);
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

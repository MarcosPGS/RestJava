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

import com.unidesc.localiza.entity.DiaSemana;
import com.unidesc.localiza.negocio.service.DiaSemanaService;

@CrossOrigin
@RestController
@RequestMapping(value="/api")
public class DiaSemanaResource {
	
	@Autowired
	DiaSemanaService diaSemanaService;
	
	
	@GetMapping("/diasemana/descricao/{descricao}")
	public ResponseEntity<List<DiaSemana>> buscaPorDiaSemana(@PathVariable String descricao){
		List<DiaSemana> diaSemanaEncontrado = diaSemanaService.buscarPorDiaSemana(descricao);
		if(diaSemanaEncontrado == null) {
			return ok(null);
		}
		return ok(diaSemanaEncontrado);
	}
	
	@GetMapping("/diasemana")
	public List<DiaSemana> buscaTodos(){
		return diaSemanaService.buscarTodosDias();
	}
	
	@PostMapping("/diasemana")
	public DiaSemana salvaDiaSemana(@RequestBody DiaSemana diaSemana) {
		return diaSemanaService.salvarDiaSemana(diaSemana);
	}
	
	@PutMapping("/diasemana")
	public DiaSemana atualizaDiaSemana(@RequestBody DiaSemana diaSemana) {
		return diaSemanaService.atualizarDiaSemana(diaSemana);
	}
	
	@DeleteMapping("/diasemana")
	public void deletaDiaSemana(@RequestBody DiaSemana diaSemana) {
		diaSemanaService.deletarDiaSemana(diaSemana);
	}

}

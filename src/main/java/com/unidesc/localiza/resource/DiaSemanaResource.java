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

import com.unidesc.localiza.entity.DiaSemana;
import com.unidesc.localiza.exceptions.DiaSemanaDuplicadoException;
import com.unidesc.localiza.negocio.service.DiaSemanaService;

@CrossOrigin
@RestController
@RequestMapping(value="/diasemana")
public class DiaSemanaResource {
	
	@Autowired
	DiaSemanaService diaSemanaService;
	
	
	@GetMapping("/{descricao}")
	public ResponseEntity<List<DiaSemana>> buscaPorDiaSemana(@PathVariable String descricao){
		List<DiaSemana> diaSemanaEncontrado = diaSemanaService.buscarPorDiaSemana(descricao);
		if(diaSemanaEncontrado == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(diaSemanaEncontrado);
		}
		return ResponseEntity.ok().body(diaSemanaEncontrado);
	}
	
	@GetMapping()
	public List<DiaSemana> buscaTodos(){
		return diaSemanaService.buscarTodosDias();
	}
	
	@PostMapping()
	public ResponseEntity<Object> salvaDiaSemana(@RequestBody DiaSemana diaSemana) {
		try {
			return ResponseEntity.ok().body(diaSemanaService.salvarDiaSemana(diaSemana));
		} catch (DiaSemanaDuplicadoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
	@PutMapping()
	public DiaSemana atualizaDiaSemana(@RequestBody DiaSemana diaSemana) {
		return diaSemanaService.atualizarDiaSemana(diaSemana);
	}
	
	@DeleteMapping("/diasemana/{idDiaSemana}")
	public void deletaDiaSemana(@PathVariable Long idDiaSemana) {
		diaSemanaService.deletarDiaSemana(idDiaSemana);
	}

}

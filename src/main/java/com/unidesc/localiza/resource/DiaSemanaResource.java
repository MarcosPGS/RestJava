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

import com.unidesc.localiza.entity.DiaSemana;
import com.unidesc.localiza.negocio.service.DiaSemanaService;

@CrossOrigin
@RestController
@RequestMapping(value="/api")
public class DiaSemanaResource {
	
	@Autowired
	DiaSemanaService diaSemanaService;
	
	@GetMapping("/diasemana")
	public List<DiaSemana> buscaTodos(){
		return diaSemanaService.buscarTodosDias();
	}
	
	@PostMapping("/diasemana")
	public DiaSemana criaDiaSemana(@RequestBody DiaSemana diaSemana) {
		return diaSemanaService.criarDiaSemana(diaSemana);
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

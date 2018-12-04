package com.unidesc.localiza.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unidesc.localiza.entity.Disciplina;
import com.unidesc.localiza.negocio.service.DisciplinaService;

@RestController
@RequestMapping(value="/api")
public class DisciplinaResource {

	
	@Autowired
	DisciplinaService disciplinaService;
	
	//endpoint buscar todas as disciplinas
	@GetMapping("/disciplina")
	public List<Disciplina> buscaTodasDisciplinas(){ 
		return disciplinaService.buscarTodasDisciplina();
	}
	
	//endpoint criar disciplina
	@PostMapping("/disciplina")
	public Disciplina salvaDisciplina(@RequestBody Disciplina disciplina) {
		return disciplinaService.salvarDisciplina(disciplina);
	}
	
	//endpoint atualizar disciplina
	@PutMapping("disciplina")
	public Disciplina atualizarDisciplina(@RequestBody Disciplina disciplina) {
		return disciplinaService.atualizarDisciplina(disciplina);
	}
	
	//endpoint Deletar disiciplina
	@DeleteMapping("/disciplina")
	public void deletaDisciplina(@RequestBody Disciplina disciplina ) {
		disciplinaService.deletarDisciplina(disciplina);
	}
	
}

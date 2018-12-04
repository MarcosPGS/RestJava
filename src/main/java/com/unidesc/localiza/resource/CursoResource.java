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

import com.unidesc.localiza.entity.Curso;
import com.unidesc.localiza.negocio.service.CursoService;

@RestController
@RequestMapping(value="/api")
public class CursoResource {

	@Autowired
	CursoService cursoService;
	
	//busca todos os cursos
	@GetMapping("/curso")
	public List<Curso> buscaTodosCursos(){ 
		return cursoService.buscarTodosCurso();
	}	
		
	//criar o curso
	@PostMapping("/curso")
	public Curso salvaCurso(@RequestBody Curso curso) {
		return cursoService.salvarCurso(curso);
	}
	
	//atualizar curso
	@PutMapping("/curso")
	public Curso atualizaCurso(@RequestBody Curso curso) {
		return cursoService.atualizarCurso(curso);
	}
	
	//deletar curso
	@DeleteMapping("/curso")
	public void deletaCurso(@RequestBody Curso curso) {
		cursoService.deletarCurso(curso);
	}
	
}

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

import com.unidesc.localiza.entity.Curso;
import com.unidesc.localiza.negocio.service.CursoService;

@CrossOrigin
@RestController
@RequestMapping(value="/api")
public class CursoResource {

	@Autowired
	CursoService cursoService;
	
	//endpoint busca todos os cursos
	@GetMapping("/curso")
	public List<Curso> buscaTodosCursos(){ 
		return cursoService.buscarTodosCurso();
	}	
		
	//endpoint criar o curso
	@PostMapping("/curso")
	public Curso salvaCurso(@RequestBody Curso curso) {
		return cursoService.salvarCurso(curso);
	}
	
	//endpoint atualizar curso
	@PutMapping("/curso")
	public Curso atualizaCurso(@RequestBody Curso curso) {
		return cursoService.atualizarCurso(curso);
	}
	
	//endpoint deletar curso
	@DeleteMapping("/curso")
	public void deletaCurso(@RequestBody Curso curso) {
		cursoService.deletarCurso(curso);
	}
	
}

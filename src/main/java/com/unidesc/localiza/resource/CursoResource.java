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

import com.unidesc.localiza.entity.Curso;
import com.unidesc.localiza.exceptions.CursoDuplicadoException;
import com.unidesc.localiza.negocio.service.CursoService;

@CrossOrigin
@RestController
@RequestMapping(value="/api")
public class CursoResource {

	@Autowired
	CursoService cursoService;
	
	
	 
	//endpoint buscar todos os cursos
	@GetMapping("/curso")
	public List<Curso> buscaTodosCursos(){ 
		return cursoService.buscarTodosCurso();
	}	
	
	//endpoint buscar os cursos por nome
		@GetMapping("/curso/nome/{nome}")
		public ResponseEntity<List<Curso>> buscaNomeCurso(@PathVariable String nome){
			List<Curso> cursoEncotrado = cursoService.buscarPorNome(nome);
			if(cursoEncotrado == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cursoEncotrado);
			}
			return ResponseEntity.ok().body(cursoEncotrado);
		}
	
	
	//endpoint criar o curso
	@PostMapping("/curso")
	public ResponseEntity<Object> salvaCurso(@RequestBody Curso curso) {
		try {
			return ResponseEntity.ok().body(cursoService.salvarCurso(curso));
		} catch (CursoDuplicadoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
	//endpoint atualizar curso
	@PutMapping("/curso")
	public Curso atualizaCurso(@RequestBody Curso curso) {
		return cursoService.atualizarCurso(curso);
	}
	
	//endpoint deletar curso
	@DeleteMapping("/curso/{idCurso}")
	public void deletaCurso(@PathVariable Long idCurso) {
		cursoService.deletarCurso(idCurso);
	}
	
}

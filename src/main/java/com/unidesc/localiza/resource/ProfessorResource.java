package com.unidesc.localiza.resource;

import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unidesc.localiza.entity.Professor;
import com.unidesc.localiza.negocio.service.ProfessorService;
import com.unidesc.localiza.repository.ProfessorRepository;

@RestController
@RequestMapping(value="/api")
public class ProfessorResource {

	@Autowired
	ProfessorService professorService;
	
	//endpoint buscar todos os professor
	@GetMapping("/professor") 
	public List<Professor>buscaTodos(){
		return professorService.buscarTodos();
	}
	
	//endpoint busca por nome
	@GetMapping("/professor/nome/{nome}")
	public ResponseEntity<Professor> buscaNome(@PathVariable String nome) {
		Professor professorResultado = professorService.buscarPorNome(nome);
		if(professorResultado == null){
            return ok(null);
        }
        return ok(professorResultado);
		
	}
	
	//endpoint criar professor
	@PostMapping("/professor")
	public Professor salvaProfessor(@RequestBody Professor professor) {
		return professorService.salvarProfessor(professor);
	}
	
	//endpoint atualizar professor
	@PutMapping("/professor")
	public Professor atualizaProfessor(@RequestBody Professor professor) {
		return professorService.atualizarProfessor(professor);
	}
	
	//endpoint deleatr professor
	@DeleteMapping("/professor")
	public void deletarProfessor(@RequestBody Professor professor) {
		professorService.deletarProfessor(professor);
	}
	
	
	
	
	
	
}

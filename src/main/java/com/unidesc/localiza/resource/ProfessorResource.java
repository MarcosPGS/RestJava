package com.unidesc.localiza.resource;

import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	
	@GetMapping("/professor") //endpoint buscar todos os professor
	public List<Professor>buscaTodos(){
		return professorService.buscarTodos();
	}
	
	@GetMapping("/professor/nome/{nome}")
	public ResponseEntity<Professor> buscaNome(@PathVariable String nome) {
		Professor professorResultado = professorService.buscarPorNome(nome);
		if(professorResultado == null){
            return ok(null);
        }
        return ok(professorResultado);
		
	}
	
	
}

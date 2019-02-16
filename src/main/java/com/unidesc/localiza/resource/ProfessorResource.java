package com.unidesc.localiza.resource;

//import static org.springframework.http.ResponseEntity.ok;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.unidesc.localiza.entity.Professor;
import com.unidesc.localiza.exceptions.ProfessorDuplicadoException;
import com.unidesc.localiza.negocio.service.ProfessorService;
import com.unidesc.localiza.negocio.to.FiltroProfessor;

@CrossOrigin // serve para resolver o problema de domínio cruzado
@RestController
@RequestMapping(value = "/api")
public class ProfessorResource {

	@Autowired
	ProfessorService professorService;

	@GetMapping("/professor")
	public List<Professor> buscaTodos() {
		return professorService.buscarTodos();
	}

	// endpoint busca por nome
	@GetMapping("/professor/nome/{nome}")
	public ResponseEntity<List<Professor>> buscaNome(@PathVariable String nome) {
		List<Professor> professorResultado = professorService.buscarPorNome(nome);
		if (professorResultado == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(professorResultado);
		}

		return ResponseEntity.ok().body(professorResultado);

	}

	// endpoint busca por nome Paginada
	@GetMapping("/professor/nomepaginacao") // Page = ele ja é uma lista;
	public Page<Professor> buscaNomeP(String nome, Pageable pageable) {

		return professorService.buscarPorNomePaginado(nome, pageable);
	}

	// endpoint pesquisar todos os professor
	@PostMapping("/professor/pesquissar")
	public List<Professor> pesquisar(@RequestBody FiltroProfessor f) {
		return professorService.pesquisar(f);
	}

	// endpoint criar professor
	@PostMapping("/professor")
	public ResponseEntity<Object> salvaProfessor(@RequestBody Professor professor) {
		try {
			return ResponseEntity.ok().body(professorService.salvarProfessor(professor));
		} catch (ProfessorDuplicadoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}

	// endpoint atualizar professor
	@PutMapping("/professor")
	public Professor atualizaProfessor(@RequestBody Professor professor) {
		return professorService.atualizarProfessor(professor);
	}

	// endpoint deletar professor
	@DeleteMapping("/professor/{idProfessor}")
	public void deletarProfessor(@PathVariable Long idProfessor) {
		professorService.deletarProfessor(idProfessor);
	}

}

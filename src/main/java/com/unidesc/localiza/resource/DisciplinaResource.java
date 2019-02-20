package com.unidesc.localiza.resource;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

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

import com.unidesc.localiza.entity.Disciplina;
import com.unidesc.localiza.exceptions.DisciplinaDuplicadaExcepton;
import com.unidesc.localiza.negocio.service.DisciplinaService;

@CrossOrigin
@RestController
@RequestMapping(value="/disciplina")
public class DisciplinaResource {

	
	@Autowired
	DisciplinaService disciplinaService;
	
	//endpoint buscar todas as disciplinas
	@GetMapping()
	public List<Disciplina> buscaTodasDisciplinas(){ 
		return disciplinaService.buscarTodasDisciplina();
	}
	
	//endpoint buscar as disciplina por nome
		@GetMapping("/{nome}")
		public ResponseEntity<List<Disciplina>> buscaNomeDisciplina(@PathVariable String nome){
			List<Disciplina> disciplinaEncontrada = disciplinaService.buscarPorNome(nome);
			if(disciplinaEncontrada == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(disciplinaEncontrada);
			}
			return ResponseEntity.ok().body(disciplinaEncontrada);
		}
		
		//endpoint busca por nome Paginada
		@GetMapping("/nomepaginacao") //Page = ele ja é uma lista;
		public Page<Disciplina> buscaNomePagina(String nome,Pageable pageable){
			return disciplinaService.buscarPorNomePaginaDisciplina(nome, pageable);
		}
		
	
	//endpoint criar disciplina
	@PostMapping()
	public ResponseEntity<Object> salvaDisciplina(@RequestBody Disciplina disciplina) {
		try {
			return ResponseEntity.ok().body(disciplinaService.salvarDisciplina(disciplina));
		} catch (DisciplinaDuplicadaExcepton e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
	//endpoint atualizar disciplina
	@PutMapping()
	public Disciplina atualizarDisciplina(@RequestBody Disciplina disciplina) {
		return disciplinaService.atualizarDisciplina(disciplina);
	}
	
	//endpoint Deletar disiciplina
	@DeleteMapping("/{idDisciplina}")
	public void deletaDisciplina(@PathVariable Long idDisciplina) {
		disciplinaService.deletarDisciplina(idDisciplina);
	}
	
}

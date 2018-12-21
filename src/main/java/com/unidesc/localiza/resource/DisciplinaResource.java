package com.unidesc.localiza.resource;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.unidesc.localiza.negocio.service.DisciplinaService;

@CrossOrigin
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
	
	//endpoint buscar as disciplina por nome
		@GetMapping("/disciplina/nome/{nome}")
		public ResponseEntity<List<Disciplina>> buscaNomeDisciplina(@PathVariable String nome){
			List<Disciplina> disciplinaEncontrad = disciplinaService.buscarPorNome(nome);
			if(disciplinaEncontrad == null) {
				return ok(null);
			}
			return ok(disciplinaEncontrad);
		}
		
		//endpoint busca por nome Paginada
		@GetMapping("/disciplina/nomePaginacao") //Page = ele ja é uma lista;
		public Page<Disciplina> buscaNomePagina(String nome,Pageable pageable){
			return disciplinaService.buscarPorNomePaginaDisciplina(nome, pageable);
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

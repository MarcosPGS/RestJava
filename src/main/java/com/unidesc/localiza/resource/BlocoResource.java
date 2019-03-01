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

import com.unidesc.localiza.entity.Bloco;
import com.unidesc.localiza.entity.Curso;
import com.unidesc.localiza.exceptions.BlocoDuplicadoException;
import com.unidesc.localiza.exceptions.CursoDuplicadoException;
import com.unidesc.localiza.negocio.service.BlocoService;
import com.unidesc.localiza.negocio.service.CursoService;

@CrossOrigin
@RestController
@RequestMapping("/bloco")
public class BlocoResource {
	
	@Autowired
	private BlocoService blocoService;
	
	
	 
	//endpoint buscar todos os cursos
	@GetMapping("")
	public List<Bloco> buscaTodosCursos(){ 
		return blocoService.listaTodos();
	}	

	//endpoint criar o curso
	@PostMapping()
	public ResponseEntity<Object> salvaBloco(@RequestBody Bloco bloco) {
		
			try {
				return ResponseEntity.ok().body(blocoService.salvarBloco(bloco));
			} catch (BlocoDuplicadoException e) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
			}
		
	}
	
	//endpoint atualizar curso
	@PutMapping()
	public Bloco atualizaBloco(@RequestBody Bloco bloco) {
		return blocoService.atualizarBloco(bloco);
	}
	
	//endpoint deletar curso
	@DeleteMapping("/{idBloco}")
	public void deletaCurso(@PathVariable Long idBloco) {
		blocoService.deletarBloco(idBloco);
	}
	

}

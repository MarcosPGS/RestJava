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
import com.unidesc.localiza.entity.Sala;
import com.unidesc.localiza.exceptions.BlocoDuplicadoException;
import com.unidesc.localiza.exceptions.SalaDuplicadaException;
import com.unidesc.localiza.negocio.service.SalaService;

@CrossOrigin
@RestController
@RequestMapping("/sala")
public class SalaResource {

	@Autowired
	private SalaService salaService;
	
	//endpoint buscar todos os cursos
		@GetMapping("")
		public List<Sala> buscaTodosSalas(){ 
			return salaService.listaTodos();
		}	

		//endpoint criar o curso
		@PostMapping()
	public ResponseEntity<Object> salvaSala(@RequestBody Sala sala) {

		try {
			return ResponseEntity.ok().body(salaService.salvarSala(sala));
		} catch (SalaDuplicadaException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
		
		//endpoint atualizar curso
		@PutMapping()
		public Sala atualizaSala(@RequestBody Sala sala) {
			return salaService.atualizarSala(sala);
		}
		
		//endpoint deletar curso
		@DeleteMapping("/{idSala}")
		public void deletaSala(@PathVariable Long idSala) {
			salaService.deletarSala(idSala);
		}
}

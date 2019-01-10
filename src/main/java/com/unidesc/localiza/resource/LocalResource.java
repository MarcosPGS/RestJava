package com.unidesc.localiza.resource;

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

import com.unidesc.localiza.entity.Local;
import com.unidesc.localiza.entity.Professor;
import com.unidesc.localiza.exceptions.LocalDuplicadoException;
import com.unidesc.localiza.negocio.service.LocalService;

@CrossOrigin
@RestController
@RequestMapping(value="/api")
public class LocalResource {
	
	@Autowired
	LocalService localService;
	
	@GetMapping("/local")
	public List<Local> buscaLocal(){
		return localService.buscarTodosLocais();
	}
	

	
	@GetMapping("/local/bloco/{bloco}")
	public ResponseEntity<List<Local>> buscaBloco(@PathVariable String bloco){
		List<Local> blocoEncontrado = localService.buscarPorBloco(bloco);
		if(blocoEncontrado == null) {
			return ResponseEntity.ok(null);
		}
		return ResponseEntity.ok(blocoEncontrado);
	}
	
	@PostMapping("/local")
	public ResponseEntity<Object> salvaLocal(@RequestBody Local local) {
		try {
			return ResponseEntity.ok().body(localService.salvarLocal(local));
		} catch (LocalDuplicadoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
	@PutMapping("/local")
	public Local atualizaLocal(@RequestBody Local local) {
		return localService.atualizarLocal(local);
	}
	
	@DeleteMapping("/local")
	public void deletaLocal(@RequestBody Local local) {
		localService.deletarLocal(local);
	}

}

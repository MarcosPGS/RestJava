package com.unidesc.localiza.resource;

import java.util.List;
import static org.springframework.http.ResponseEntity.ok;
import org.springframework.beans.factory.annotation.Autowired;
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
			return ok(null);
		}
		return ok(blocoEncontrado);
	}
	
	@PostMapping("/local")
	public Local salvaLocal(@RequestBody Local local) {
		return localService.salvarLocal(local);
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

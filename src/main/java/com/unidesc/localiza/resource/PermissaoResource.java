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

import com.unidesc.localiza.entity.Permissao;
import com.unidesc.localiza.exceptions.PermissaoDuplicadoException;
import com.unidesc.localiza.negocio.service.PermissaoService;

@CrossOrigin
@RestController
@RequestMapping(value="/permissao")
public class PermissaoResource {

	@Autowired
	PermissaoService permissaoService;
	
	@GetMapping()
	public List<Permissao> buscarTodos(){
		return permissaoService.buscarTodasPermissao();
	}
	
	@PostMapping()
	public ResponseEntity<Object> criarPermissao(@RequestBody Permissao permissao) {
		try {
			return ResponseEntity.ok().body( permissaoService.salvarPermissao(permissao));
		} catch (PermissaoDuplicadoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	@PutMapping()
	public Permissao atualizarPermissao(@RequestBody Permissao permissao) {
		return permissaoService.atualizarPermissao(permissao);
	}
	 
	@DeleteMapping("/{idPermissao}")
	public void deletarPermissao(@PathVariable Long idPermissao) {
		permissaoService.deletarPermissao(idPermissao);
	}
	
}

package com.unidesc.localiza.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unidesc.localiza.entity.Permissao;
import com.unidesc.localiza.negocio.service.PermissaoService;

@CrossOrigin
@RestController
@RequestMapping(value="/api")
public class PermissaoResource {

	@Autowired
	PermissaoService permissaoService;
	
	@GetMapping("/permissao")
	public List<Permissao> buscarTodos(){
		return permissaoService.buscarTodasPermissao();
	}
	
	public Permissao criarPermissao(@RequestBody Permissao permissao) {
		return permissaoService.criarPermissao(permissao);
	}
	
	public Permissao atualizarPermissao(@RequestBody Permissao permissao) {
		return permissaoService.atualizarPermissao(permissao);
	}
	 
	
	public void deletarPermissao(@RequestBody Permissao permissao) {
		permissaoService.deletarPermissao(permissao);
	}
	
}

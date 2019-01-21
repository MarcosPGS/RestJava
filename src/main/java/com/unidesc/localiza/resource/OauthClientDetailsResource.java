package com.unidesc.localiza.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unidesc.localiza.entity.OauthClientDetails;
import com.unidesc.localiza.negocio.service.OauthClientDetailsService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class OauthClientDetailsResource {

	
	@Autowired
	OauthClientDetailsService oauthClientDetailsService;
	
	@GetMapping("/oauth")
	public List<OauthClientDetails> ListaTodos(){
		return oauthClientDetailsService.ListarTodos();
	}
	
	@PostMapping("/oauth")
	public OauthClientDetails salvaOauth(@RequestBody OauthClientDetails oauthClientDetails) {
		return oauthClientDetailsService.salvarOauthClientDetails(oauthClientDetails);
	}
	
	@PutMapping("/oauth")
	public OauthClientDetails atualizaOauth(@RequestBody OauthClientDetails oauthClientDetails) {
		return oauthClientDetailsService.atualizarOauthClientDetails(oauthClientDetails);
	}
	
	@DeleteMapping("/oauth/{clientId}")
	public void deletaOauth(@PathVariable String clientId) {
		oauthClientDetailsService.deletarOauthClientDetails(clientId);
	}
	
}

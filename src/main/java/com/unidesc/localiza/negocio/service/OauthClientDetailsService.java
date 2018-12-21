package com.unidesc.localiza.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import com.unidesc.localiza.entity.OauthClientDetails;
import com.unidesc.localiza.repository.OauthClientDetailsRepository;

@Service
public class OauthClientDetailsService {

	@Autowired
	OauthClientDetailsRepository oauthClientDetailsRepository;
	
	public List<OauthClientDetails> ListarTodos(){
		return oauthClientDetailsRepository.findAll();
	}
	
	public OauthClientDetails salvarOauthClientDetails(@RequestBody OauthClientDetails oauthClientDetails) {
		return oauthClientDetailsRepository.save(oauthClientDetails);
	}
	
	public OauthClientDetails atualizarOauthClientDetails(@RequestBody OauthClientDetails oauthClientDetails) {
		return oauthClientDetailsRepository.save(oauthClientDetails);
	}
	
	public void deletarOauthClientDetails(@RequestBody OauthClientDetails oauthClientDetails) {
		 oauthClientDetailsRepository.delete(oauthClientDetails);
	}
}

package com.unidesc.localiza.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.unidesc.localiza.entity.Professor;
import com.unidesc.localiza.entity.Usuario;

@Repository
public class UsuarioRepositoryImpl {

	
	@PersistenceContext
 	EntityManager manager;
	//==================================== Busca Por nome ===========================================

	public Usuario buscarLogin(String login) {
		Usuario usuarioEncontado = null;
		try {
			CriteriaBuilder builder = manager.getCriteriaBuilder();
			CriteriaQuery<Usuario> usuarioCR = builder.createQuery(Usuario.class);
			
			Root<Usuario> usuarioRoot = usuarioCR.from(Usuario.class);
			Predicate[] predicates = criarRestricao(login, builder, usuarioRoot);
			usuarioCR.where(predicates);
			
			TypedQuery<Usuario>typedQuery = manager.createQuery(usuarioCR);
			
			usuarioEncontado = typedQuery.getSingleResult();
					
			return  usuarioEncontado;
			
			
		} catch (Exception e) {
			return usuarioEncontado;
		}
		
	}
	private Predicate[] criarRestricao(String login, CriteriaBuilder builder, Root<Usuario> usuarioRoot) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.isEmpty(login)) {
			predicates.add(builder.equal(usuarioRoot.get("login"), login));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
}

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


import com.unidesc.localiza.entity.Permissao;
import com.unidesc.localiza.repository.PermissaoRepositoryQuery;

@Repository
public class PermissaoRepositoryImpl implements PermissaoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Permissao buscarPorPermissaoUnica(String permissao) {
			
		Permissao permissaoEncontrado = null;
		try {
			CriteriaBuilder builder = manager.getCriteriaBuilder();
			CriteriaQuery<Permissao> permissaoCq = builder.createQuery(Permissao.class);
			
			Root<Permissao> permissaoRoot = permissaoCq.from(Permissao.class);
			Predicate[] predicates = criarRestricaoUnico(permissao,builder, permissaoRoot);
			permissaoCq.where(predicates);
			
			TypedQuery<Permissao> typedQuery = manager.createQuery(permissaoCq);
			
			permissaoEncontrado = typedQuery.getSingleResult();
			
			return permissaoEncontrado;
			
		} catch (Exception e) {
			return permissaoEncontrado;
		}
		
		
	}


	private Predicate[] criarRestricaoUnico(String permissao, CriteriaBuilder builder, Root<Permissao> permissaoRoot) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.isEmpty(permissao)) {
			predicates.add(builder.equal(permissaoRoot.get("permissao"), permissao));		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}


	
}

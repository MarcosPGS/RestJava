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

import com.unidesc.localiza.entity.Local;
import com.unidesc.localiza.repository.LocalRepositoryQuery;

@Repository
public class LocalRepositoryImpl implements LocalRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Local> buscaPorBloco(String bloco) {
		List<Local> blocoEncontrado= null;
		
		try {
		CriteriaBuilder builder =manager.getCriteriaBuilder();	
		CriteriaQuery<Local> blocoCq = builder.createQuery(Local.class);
		Root<Local> blocoRoot = blocoCq.from(Local.class);
		
		Predicate[] predicates = criarRestricao(bloco, builder,blocoRoot);
		blocoCq.where(predicates);
		
		TypedQuery<Local> typedQuery =manager.createQuery(blocoCq);
		
		blocoEncontrado = typedQuery.getResultList();
		return blocoEncontrado;
		} catch (Exception e) {
			return blocoEncontrado;
		}
	}

	private Predicate[] criarRestricao(String bloco, CriteriaBuilder builder, Root<Local> blocoRoot) {
		List<Predicate> predicates = new ArrayList<>();
		if(!StringUtils.isEmpty(bloco)) {
			predicates.add(builder.like(builder.lower(blocoRoot.get("bloco")), "%" + (bloco.toLowerCase()) + "%"));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}

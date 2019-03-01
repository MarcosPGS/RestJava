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

import com.unidesc.localiza.entity.Bloco;
import com.unidesc.localiza.repository.BlocoRepositoryQuery;

@Repository
public class BlocoRepositoryImpl implements BlocoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Bloco buscarPorNome(String descricao) {
		Bloco blocoEncontrado = null;
		try {
			CriteriaBuilder builder = manager.getCriteriaBuilder();
			CriteriaQuery<Bloco> blocoCq = builder.createQuery(Bloco.class);
			
			Root<Bloco> blocoRoot = blocoCq.from(Bloco.class);
			Predicate[] predicates = criarRestricaoUnico(descricao,builder, blocoRoot);
			blocoCq.where(predicates);
			
			TypedQuery<Bloco> typedQuery = manager.createQuery(blocoCq);
			
			blocoEncontrado = typedQuery.getSingleResult();
			
			return blocoEncontrado;
			
		} catch (Exception e) {
			return blocoEncontrado;
		}
		
		
	}


	private Predicate[] criarRestricaoUnico(String descricao, CriteriaBuilder builder, Root<Bloco> blocoRoot) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.isEmpty(descricao)) {
			
			predicates.add(builder.like( builder.lower( blocoRoot.get("descricao")),  "%" +(descricao.toLowerCase())+ "%"   ));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	

}

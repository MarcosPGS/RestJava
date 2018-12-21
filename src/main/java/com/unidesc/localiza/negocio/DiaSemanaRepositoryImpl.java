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

import com.unidesc.localiza.entity.DiaSemana;
import com.unidesc.localiza.repository.DiaSemanaRepositoryQuery;

@Repository
public class DiaSemanaRepositoryImpl implements DiaSemanaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	@Override
	public List<DiaSemana> buscarPorDiaSemana(String descricao) {
		List<DiaSemana> diaSemanaEncontrado = null;
		
		try {
			CriteriaBuilder builder = manager.getCriteriaBuilder();
			CriteriaQuery<DiaSemana> diaSemanaCq = builder.createQuery(DiaSemana.class);
			Root<DiaSemana> diaSemanaRoot = diaSemanaCq.from(DiaSemana.class);
			
			Predicate[] predicates= criarRestricoes(descricao,builder, diaSemanaRoot);
			diaSemanaCq.where(predicates);
			
			TypedQuery< DiaSemana> typedQuery = manager.createQuery(diaSemanaCq);
			 
			diaSemanaEncontrado = typedQuery.getResultList();
			return diaSemanaEncontrado;
			
		} catch (Exception e) {
			return diaSemanaEncontrado;
		}
		
	}
	private Predicate[] criarRestricoes(String descricao, CriteriaBuilder builder, Root<DiaSemana> diaSemanaRoot) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(descricao)) {
			predicates.add(builder.like(builder.lower(diaSemanaRoot.get("descricao")), "%"+(descricao.toLowerCase())+"%" ));
			
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}

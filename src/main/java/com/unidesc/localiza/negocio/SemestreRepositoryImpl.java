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

import com.unidesc.localiza.entity.Semestre;
import com.unidesc.localiza.repository.SemestreRepositoryQuery;

@Repository
public class SemestreRepositoryImpl implements SemestreRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Semestre buscarPorSemestre(String semestre) {
		Semestre semestreEncontrado = null;
		
		try {
			CriteriaBuilder builder = manager.getCriteriaBuilder();
			CriteriaQuery<Semestre> semestreCq = builder.createQuery(Semestre.class);
			Root<Semestre> semestreRooT = semestreCq.from(Semestre.class);
			Predicate[] predicates =criarRestricao(semestre, builder, semestreRooT);
			semestreCq.where(predicates);
			TypedQuery<Semestre> typedQuery = manager.createQuery(semestreCq);
			
			semestreEncontrado = typedQuery.getSingleResult();
			
			return semestreEncontrado;
			
			
		} catch (Exception e) {
			return semestreEncontrado;
		}
	}

	private Predicate[] criarRestricao(String semestre, CriteriaBuilder builder, Root<Semestre> semestreRooT) {
		List<Predicate> predicates = new ArrayList<>();
		if(!StringUtils.isEmpty(semestre)) {
			//predicates.add(builder.equal(semestreRooT.get("semestre"),semestre));
			predicates.add(builder.like( builder.lower( semestreRooT.get("semestre")),  "%" +(semestre.toLowerCase())+ "%"   ));	

		}
		return predicates.toArray(new Predicate[predicates.size()]);
		
	}

}

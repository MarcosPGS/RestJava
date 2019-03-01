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
import com.unidesc.localiza.entity.Sala;
import com.unidesc.localiza.repository.SalaRepositoryQuery;

@Repository
public class SalaRepositoryImpl implements SalaRepositoryQuery{
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Sala buscarPorNome(String descricao) {
		Sala salaEncontrado = null;
		try {
			CriteriaBuilder builder = manager.getCriteriaBuilder();
			CriteriaQuery<Sala> salaCq = builder.createQuery(Sala.class);
			
			Root<Sala> salaRoot = salaCq.from(Sala.class);
			Predicate[] predicates = criarRestricaoUnico(descricao,builder, salaRoot);
			salaCq.where(predicates);
			
			TypedQuery<Sala> typedQuery = manager.createQuery(salaCq);
			
			salaEncontrado = typedQuery.getSingleResult();
			
			return salaEncontrado;
			
		} catch (Exception e) {
			return salaEncontrado;
		}
		
		
	}

	private Predicate[] criarRestricaoUnico(String descricao, CriteriaBuilder builder, Root<Sala> salaRoot) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.isEmpty(descricao)) {
			
			predicates.add(builder.like( builder.lower( salaRoot.get("descricao")),  "%" +(descricao.toLowerCase())+ "%"   ));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}


	


}

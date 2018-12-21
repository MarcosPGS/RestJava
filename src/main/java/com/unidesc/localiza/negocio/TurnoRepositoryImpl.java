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

import com.unidesc.localiza.entity.Turno;
import com.unidesc.localiza.repository.TurnoRepositoryQuery;

@Repository
public class TurnoRepositoryImpl implements TurnoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	@Override
	public Turno buscarPorTurno(String descricao) {
		Turno turnoEncontrado = null;
		try {
			CriteriaBuilder builder= manager.getCriteriaBuilder();
			CriteriaQuery<Turno> turnoCq = builder.createQuery(Turno.class);
			Root<Turno> turnoRoot = turnoCq.from(Turno.class);
			Predicate[] predicates = criarResticao(descricao, builder, turnoRoot);
			turnoCq.where(predicates);
			
			TypedQuery<Turno> typedQuery = manager.createQuery(turnoCq);
			
			turnoEncontrado = typedQuery.getSingleResult();
			
			return turnoEncontrado;
		} catch (Exception e) {
			return turnoEncontrado;
		}
		
	}
	private Predicate[] criarResticao(String descricao, CriteriaBuilder builder, Root<Turno> turnoRoot) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(descricao)) {
			predicates.add(builder.like(builder.lower(turnoRoot.get("descricao")), "%" +(descricao.toLowerCase())+"%"));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}

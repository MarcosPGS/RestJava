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
	
	
	

	@Override
	public Local buscarPorBlocoUnico(String bloco) {
		Local blocoEncontrado= null;
		
		try {
		CriteriaBuilder builder =manager.getCriteriaBuilder();	
		CriteriaQuery<Local> blocoCq = builder.createQuery(Local.class);
		Root<Local> blocoRoot = blocoCq.from(Local.class);
		
		Predicate[] predicates = criarRestricaoUnico(bloco, builder,blocoRoot);
		blocoCq.where(predicates);
		
		TypedQuery<Local> typedQuery =manager.createQuery(blocoCq);
		
		blocoEncontrado = typedQuery.getSingleResult();
		return blocoEncontrado;
		} catch (Exception e) {
			return blocoEncontrado;
		}
	}

	private Predicate[] criarRestricaoUnico(String bloco, CriteriaBuilder builder, Root<Local> blocoRoot) {
		List<Predicate> predicates = new ArrayList<>();
		if(!StringUtils.isEmpty(bloco)) {
			predicates.add(builder.like(builder.lower(blocoRoot.get("bloco")), "%" + (bloco.toLowerCase()) + "%"));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	@Override
	public Local buscarPorSalaUnico(String sala) {
		Local blocoEncontrado= null;
		
		try {
		CriteriaBuilder builder =manager.getCriteriaBuilder();	
		CriteriaQuery<Local> salaCq = builder.createQuery(Local.class);
		Root<Local> salaRoot = salaCq.from(Local.class);
		
		Predicate[] predicates = criarRestricaoUnico2(sala, builder,salaRoot);
		salaCq.where(predicates);
		
		TypedQuery<Local> typedQuery =manager.createQuery(salaCq);
		
		blocoEncontrado = typedQuery.getSingleResult();
		return blocoEncontrado;
		} catch (Exception e) {
			return blocoEncontrado;
		}
	}

	private Predicate[] criarRestricaoUnico2(String sala, CriteriaBuilder builder, Root<Local> salaRoot) {
		List<Predicate> predicates = new ArrayList<>();
		if(!StringUtils.isEmpty(sala)) {
			predicates.add(builder.like(builder.lower(salaRoot.get("sala")), "%" + (sala.toLowerCase()) + "%"));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	}


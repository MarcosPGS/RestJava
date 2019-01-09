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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.unidesc.localiza.entity.Local;
import com.unidesc.localiza.entity.Professor;
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
	public Local buscarPorLocalUnico(String bloco, String sala) {
		Local localEncontrado = null;
		try {
			CriteriaBuilder builder =manager.getCriteriaBuilder();
			CriteriaQuery< Local> localCR = builder.createQuery(Local.class);
			Root<Local> localRoot = localCR.from(Local.class);
			Predicate[] predicates = criarRestricaoUnica(bloco, sala, builder, localRoot);
			localCR.where(predicates);
			
			TypedQuery<Local> typedQuery = manager.createQuery(localCR);
			
			localEncontrado = typedQuery.getSingleResult();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return localEncontrado;
	}

	private Predicate[] criarRestricaoUnica(String bloco, String sala, CriteriaBuilder builder, Root<Local> localRoot) {
		List<Predicate> predicates = new ArrayList<>();
		if((!StringUtils.isEmpty(bloco)) &&(!StringUtils.isEmpty(sala)) ) {
		predicates.add(builder.equal(localRoot.get("bloco"),bloco));
		predicates.add(builder.equal(localRoot.get("sala"),sala));
	}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	@Override
	public Page<Local> buscarLocalPaginado(String bloco, Pageable pageable) {
		try {
			CriteriaBuilder builder =manager.getCriteriaBuilder();
			CriteriaQuery< Local> localCR = builder.createQuery(Local.class);
			Root<Local> localRoot = localCR.from(Local.class);
			Predicate[] predicates = criarRestricao(bloco, builder, localRoot);
			localCR.where(predicates);
			TypedQuery<Local> typedQuery = manager.createQuery(localCR);
			
			adicionarRestricaoDePaginacao(typedQuery, pageable);
			return new PageImpl<>(typedQuery.getResultList(), pageable, total(bloco));
			
		} catch (Exception e) {
			return null;
		}
		
	}

	private Long total(String bloco) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> tabelaLocalCR = builder.createQuery(Long.class);
		
		Root<Local> localRoot = tabelaLocalCR.from(Local.class);
		Predicate[] predicates = criarRestricao(bloco, builder, localRoot);
		tabelaLocalCR.where(predicates);
		
		tabelaLocalCR.select(builder.count(localRoot));
		
		
		return manager.createQuery(tabelaLocalCR).getSingleResult();
	}
	private void adicionarRestricaoDePaginacao(TypedQuery<Local> typedQuery, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistroPorPagina = pageable.getPageSize();
		int primeiroRegistroPagina = paginaAtual * totalRegistroPorPagina;
		
		 typedQuery.setFirstResult(primeiroRegistroPagina);
		 typedQuery.setMaxResults(totalRegistroPorPagina);
		
	}
}
	
	

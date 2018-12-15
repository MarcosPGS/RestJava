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

import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.unidesc.localiza.entity.Professor;
import com.unidesc.localiza.repository.ProfessorRepositoryQuery;

@Repository
public class ProfessorRepositoryImpl implements ProfessorRepositoryQuery {
	
	@PersistenceContext
 	EntityManager manager;
	//==================================== Busca Por nome ===========================================

	@Override
	public List<Professor> buscarNome(String nome) {
		List<Professor> professorEncontado = null;
		try {
			CriteriaBuilder builder = manager.getCriteriaBuilder();
			CriteriaQuery<Professor> professorCR = builder.createQuery(Professor.class);
			
			Root<Professor> professorRoot = professorCR.from(Professor.class);
			Predicate[] predicates = criarRestricao(nome, builder, professorRoot);
			professorCR.where(predicates);
			
			TypedQuery<Professor>typedQuery = manager.createQuery(professorCR);
			
			professorEncontado = typedQuery.getResultList();
					
			return (List<Professor>) professorEncontado;
			
			
		} catch (Exception e) {
			return professorEncontado;
		}
		
	}
	private Predicate[] criarRestricao(String nome, CriteriaBuilder builder, Root<Professor> professorRoot) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.isEmpty(nome)) {
			predicates.add(builder.like( builder.lower( professorRoot.get("nome")),  "%" +(nome.toLowerCase())+ "%"   ));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	
	
	
	
	
	
	
	@Override
	public Page<Professor> buscarNomeP(String nome, Pageable pageable) {
		
		try {
			CriteriaBuilder builder = manager.getCriteriaBuilder();
			CriteriaQuery<Professor> professorCR = builder.createQuery(Professor.class);
			
			Root<Professor> professorRoot = professorCR.from(Professor.class);
			
			Predicate[] predicates = criarRestricao(nome, builder, professorRoot);
			professorCR.where(predicates);
			
			TypedQuery<Professor> typedQuery = manager.createQuery(professorCR);
			
			adicionarRestricaoDePaginacao(typedQuery, pageable);
					
			return new PageImpl<>(typedQuery.getResultList(), pageable, total(nome));
			
			
		} catch (Exception e) {
			return null;
		}
		
	}
	private Long total(String nome) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> tabelaProfessorCR = builder.createQuery(Long.class);
		
		Root<Professor> professorRoot = tabelaProfessorCR.from(Professor.class);
		Predicate[] predicates = criarRestricao(nome, builder, professorRoot);
		tabelaProfessorCR.where(predicates);
		
		tabelaProfessorCR.select(builder.count(professorRoot));
		
		
		return manager.createQuery(tabelaProfessorCR).getSingleResult();
	}
	
	
	private void adicionarRestricaoDePaginacao(TypedQuery<Professor> typedQuery, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistroPorPagina = pageable.getPageSize();
		int primeiroRegistroPagina = paginaAtual * totalRegistroPorPagina;
		
		 typedQuery.setFirstResult(primeiroRegistroPagina);
		 typedQuery.setMaxResults(totalRegistroPorPagina);
		
	}

}

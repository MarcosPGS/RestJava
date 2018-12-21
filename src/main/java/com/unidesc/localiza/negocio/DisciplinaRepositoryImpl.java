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

import com.unidesc.localiza.entity.Disciplina;
import com.unidesc.localiza.repository.DisciplinaRepositoryQuery;

@Repository
public class DisciplinaRepositoryImpl implements DisciplinaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Disciplina> buscarPorNomeDisciplina(String nome) {
	 List<Disciplina> disciplinaEncontrada = null;
	 try {
		
	
	 CriteriaBuilder builder = manager.getCriteriaBuilder();
	 CriteriaQuery<Disciplina> disciplinaCq = builder.createQuery(Disciplina.class);
	 Root<Disciplina> disciplinaRoot = disciplinaCq.from(Disciplina.class);
	 
	 Predicate[] predicates = criarRestricao(nome, builder, disciplinaRoot);
	 disciplinaCq.where(predicates);
	 
	 TypedQuery<Disciplina> typedQuery = manager.createQuery(disciplinaCq);
	 
	 disciplinaEncontrada = typedQuery.getResultList();
	 
	 return disciplinaEncontrada;
	 } catch (Exception e) {
			return disciplinaEncontrada;
		}
	 
	}

	private Predicate[] criarRestricao(String nome, CriteriaBuilder builder, Root<Disciplina> disciplinaRoot) {
		List<Predicate> predicates = new ArrayList<>();
		if(!StringUtils.isEmpty(nome)) {
			predicates.add(builder.like(builder.lower(disciplinaRoot.get("nome")),"%" +  (nome.toLowerCase())+ "%"));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	
	// Busca com Paginação
	
	@Override
	public Page<Disciplina> buscarNomePagina(String nome, Pageable pageable) {
		try {
			
			CriteriaBuilder builder= manager.getCriteriaBuilder();
			CriteriaQuery<Disciplina> disciplinaCq = builder.createQuery(Disciplina.class);
			Root<Disciplina> disciplinaRoot = disciplinaCq.from(Disciplina.class);
			
			Predicate[] predicates = criarRestricao(nome, builder, disciplinaRoot);
			disciplinaCq.where(predicates);
			
			TypedQuery<Disciplina> typedQuery = manager.createQuery(disciplinaCq);
			
			adicionarRestricaoDePaginacao(typedQuery,pageable);
			
			return new PageImpl<>(typedQuery.getResultList(), pageable, total(nome));
			
		} catch (Exception e) {
			return null;
		}
	}

	public Long total(String nome) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> tabelaDisciplinaCq =builder.createQuery(Long.class);
		
		Root<Disciplina> disciplinaRoot = tabelaDisciplinaCq.from(Disciplina.class);
		Predicate[] predicates = criarRestricao(nome, builder, disciplinaRoot);
		tabelaDisciplinaCq.where(predicates);
		
		tabelaDisciplinaCq.select(builder.count(disciplinaRoot));
		
		return manager.createQuery(tabelaDisciplinaCq).getSingleResult();
	
	}
	
	
	private void adicionarRestricaoDePaginacao(TypedQuery<Disciplina> typedQuery, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalResgitroPorPagina = pageable.getPageSize();
		int primeroRegistroPagina = paginaAtual * totalResgitroPorPagina;
		
		typedQuery.setFirstResult(primeroRegistroPagina);
		typedQuery.setMaxResults(totalResgitroPorPagina);
		
	}

	
}

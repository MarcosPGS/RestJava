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

import com.unidesc.localiza.entity.Curso;
import com.unidesc.localiza.repository.CursoRepositoryQuery;



@Repository
public class CursoRepositoryImpl implements CursoRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public List<Curso> buscarPorNomeCurso(String nome) {
		List<Curso> cursoEncontrado = null;
		try {
			CriteriaBuilder builder = manager.getCriteriaBuilder();
			CriteriaQuery<Curso> cursoCq = builder.createQuery(Curso.class);
			
			Root<Curso> cursoRoot = cursoCq.from(Curso.class);
			Predicate[] predicates = criarRestricao(nome,builder, cursoRoot);
			cursoCq.where(predicates);
			
			TypedQuery<Curso> typedQuery = manager.createQuery(cursoCq);
			
			cursoEncontrado = typedQuery.getResultList();
			
			return cursoEncontrado;
			
		} catch (Exception e) {
			return cursoEncontrado;
		}
		
		
	}


	private Predicate[] criarRestricao(String nome, CriteriaBuilder builder, Root<Curso> cursoRoot) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.isEmpty(nome)) {
			predicates.add(builder.like( builder.lower( cursoRoot.get("nome")),  "%" +(nome.toLowerCase())+ "%"   ));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}


	@Override
	public Curso buscarPorNomeUnico(String nome) {
		Curso cursoEncontrado = null;
		try {
			CriteriaBuilder builder = manager.getCriteriaBuilder();
			CriteriaQuery<Curso> cursoCq = builder.createQuery(Curso.class);
			
			Root<Curso> cursoRoot = cursoCq.from(Curso.class);
			Predicate[] predicates = criarRestricaoUnico(nome,builder, cursoRoot);
			cursoCq.where(predicates);
			
			TypedQuery<Curso> typedQuery = manager.createQuery(cursoCq);
			
			cursoEncontrado = typedQuery.getSingleResult();
			
			return cursoEncontrado;
			
		} catch (Exception e) {
			return cursoEncontrado;
		}
		
		
	}


	private Predicate[] criarRestricaoUnico(String nome, CriteriaBuilder builder, Root<Curso> cursoRoot) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.isEmpty(nome)) {
			predicates.add(builder.like( builder.lower( cursoRoot.get("nome")),  "%" +(nome.toLowerCase())+ "%"   ));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	

	

}

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
	public Professor buscarNome(String nome) {
		Professor professorEncontado = null;
		try {
			CriteriaBuilder builder = manager.getCriteriaBuilder();
			CriteriaQuery<Professor> professorCR = builder.createQuery(Professor.class);
			
			Root<Professor> professorRoot = professorCR.from(Professor.class);
			Predicate[] predicates = criarRestricao(nome, builder, professorRoot);
			professorCR.where(predicates);
			
			TypedQuery<Professor>typedQuery = manager.createQuery(professorCR);
			
			professorEncontado = typedQuery.getSingleResult();
			return professorEncontado;
			
			
		} catch (Exception e) {
			return professorEncontado;
		}
		
	}
	private Predicate[] criarRestricao(String nome, CriteriaBuilder builder, Root<Professor> professorRoot) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.isEmpty(nome)) {
			predicates.add(builder.equal(professorRoot.get("nome"),nome));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}

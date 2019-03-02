package com.unidesc.localiza.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.unidesc.localiza.entity.Bloco;
import com.unidesc.localiza.entity.Curso;
import com.unidesc.localiza.entity.Disciplina;
import com.unidesc.localiza.entity.Professor;
import com.unidesc.localiza.negocio.to.FiltroProfessor;
import com.unidesc.localiza.repository.ProfessorRepositoryQuery;

@Repository
public class ProfessorRepositoryImpl implements ProfessorRepositoryQuery {

	@PersistenceContext
	EntityManager manager;
	// ==================================== Busca Por nome
	// ===========================================

	@Override
	public List<Professor> buscarNome(String nome) {
		List<Professor> professorEncontado = null;
		try {
			CriteriaBuilder builder = manager.getCriteriaBuilder();
			CriteriaQuery<Professor> professorCR = builder.createQuery(Professor.class);

			Root<Professor> professorRoot = professorCR.from(Professor.class);
			Predicate[] predicates = criarRestricao(nome, builder, professorRoot);
			professorCR.where(predicates);

			TypedQuery<Professor> typedQuery = manager.createQuery(professorCR);

			professorEncontado = typedQuery.getResultList();

			return (List<Professor>) professorEncontado;

		} catch (Exception e) {
			return professorEncontado;
		}

	}

	private Predicate[] criarRestricao(String nome, CriteriaBuilder builder, Root<Professor> professorRoot) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.isEmpty(nome)) {
			predicates.add(builder.like(builder.lower(professorRoot.get("nome")), "%" + (nome.toLowerCase()) + "%"));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	@Override
	public Page<Professor> buscarNomePaginado(String nome, Pageable pageable) {

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

	@Override
	public Professor buscarPorMatricula(String matricula) {

		Professor professorEncontradaAtiva = null;

		try {
			CriteriaBuilder builder = manager.getCriteriaBuilder();
			CriteriaQuery<Professor> professorCr = builder.createQuery(Professor.class);

			Root<Professor> professorRoot = professorCr.from(Professor.class);

			Predicate[] predicates = restricaoMatricula(matricula, builder, professorRoot);
			professorCr.where(predicates);

			TypedQuery<Professor> query = manager.createQuery(professorCr);

			professorEncontradaAtiva = query.getSingleResult();
			return professorEncontradaAtiva;

		} catch (Exception e) {
			return professorEncontradaAtiva;

		}

	}

	private Predicate[] restricaoMatricula(String matricula, CriteriaBuilder builder, Root<Professor> professorRoot) {
		List<Predicate> predicates = new ArrayList<>();
		if (!StringUtils.isEmpty(matricula)) {
			predicates.add(builder.equal(professorRoot.get("matricula"), matricula));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	@Override
	public List<Professor> pesquisar(FiltroProfessor f) {
		List<Professor> professorEncontado = null;
		try {
			CriteriaBuilder builder = manager.getCriteriaBuilder();
			CriteriaQuery<Professor> professorCR = builder.createQuery(Professor.class);

			Root<Professor> professorRoot = professorCR.from(Professor.class);
			Join<Professor, Disciplina> disciplinas = professorRoot.join("disciplinas", JoinType.INNER);
			Join<Disciplina, Curso> cursos = disciplinas.join("cursos", JoinType.INNER);
//			Join<Disciplina, Bloco> blocos = disciplinas.join("blocos", JoinType.INNER);

			Predicate[] predicates = criarRestricao(f, builder, professorRoot, disciplinas, cursos);
			professorCR.where(predicates);

			TypedQuery<Professor> typedQuery = manager.createQuery(professorCR);

			professorEncontado = typedQuery.getResultList();

			return (List<Professor>) professorEncontado;
		} catch (Exception e) {
			return professorEncontado;
		}

	}

	private Predicate[] criarRestricao(FiltroProfessor f, CriteriaBuilder builder, Root<Professor> professorRoot,
			Join<Professor, Disciplina> disciplinas, Join<Disciplina, Curso> cursos) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(f) && !StringUtils.isEmpty(f.getProfessor())
				&& !StringUtils.isEmpty(f.getProfessor().getNome())) {
			predicates.add(builder.like(builder.lower(professorRoot.get("nome")),
					"%" + (f.getProfessor().getNome().toLowerCase()) + "%"));
		}
		if (!StringUtils.isEmpty(f) && !StringUtils.isEmpty(f.getProfessor())
				&& !StringUtils.isEmpty(f.getProfessor().getMatricula())) {
			predicates.add(builder.equal(professorRoot.get("matricula"), f.getProfessor().getMatricula()));
		}
		if (!StringUtils.isEmpty(f) && !StringUtils.isEmpty(f.getDisciplina()) && !StringUtils.isEmpty(f.getDisciplina().getIdDisciplina())) {
			predicates.add(builder.equal(disciplinas.get("idDisciplina"), f.getDisciplina().getIdDisciplina()));
		}
		if (!StringUtils.isEmpty(f) && !StringUtils.isEmpty(f.getCurso()) && !StringUtils.isEmpty(f.getCurso().getIdCurso())) {
			predicates.add(builder.equal(cursos.get("idCurso"), f.getCurso().getIdCurso()));
		}
		
//		if (!StringUtils.isEmpty(f) && !StringUtils.isEmpty(f.getBloco()) && !StringUtils.isEmpty(f.getBloco().getIdBloco())) {
//			predicates.add(builder.equal(blocos.get("idBloco"), f.getBloco().getIdBloco()));
//		}

		return predicates.toArray(new Predicate[predicates.size()]);

	}

}

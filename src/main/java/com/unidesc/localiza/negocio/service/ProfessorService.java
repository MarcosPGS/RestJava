package com.unidesc.localiza.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.unidesc.localiza.entity.Professor;
import com.unidesc.localiza.exceptions.ProfessorDuplicadoException;
import com.unidesc.localiza.negocio.to.FiltroProfessor;
import com.unidesc.localiza.repository.ProfessorRepository;

@Service
public class ProfessorService {

	@Autowired
	ProfessorRepository professorRepository;

	public List<Professor> pesquisar(FiltroProfessor f) {
		return professorRepository.pesquisar(f);
	}

	public List<Professor> buscarPorNome(String nome) {
		return professorRepository.buscarNome(nome);
	}

	public Page<Professor> buscarPorNomePaginado(String nome, Pageable pageable) {
		return professorRepository.buscarNomePaginado(nome, pageable);
	}

	public List<Professor> buscarTodos() {
		return professorRepository.findAll();
	}

	public Professor salvarProfessor(Professor professor) throws ProfessorDuplicadoException {

		if (professor != null && professor.getMatricula() != null) {

			Professor professorEncontrado = professorRepository.buscarPorMatricula(professor.getMatricula());

			if (professorEncontrado != null) {
				throw new ProfessorDuplicadoException(
						"Professor Duplicado!" + " ID: " + professorEncontrado.getIdProfessor() + " Matricula: "
								+ professorEncontrado.getMatricula() + "Nome: " + professorEncontrado.getNome());
			}

		}

		return professorRepository.save(professor);
	}

	public Professor atualizarProfessor(Professor professor) {
		return professorRepository.save(professor);
	}

	public void deletarProfessor(Long idProfessor) {
		professorRepository.deleteById(idProfessor);
	}

}

package com.unidesc.localiza.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.unidesc.localiza.entity.Professor;
import com.unidesc.localiza.exceptions.ProfessorDuplicadoException;
import com.unidesc.localiza.repository.ProfessorRepository;

@Service
public class ProfessorService {

	@Autowired
	ProfessorRepository professorRepository;
	
	public List<Professor> buscarPorNome(String nome) {
		return professorRepository.buscarNome(nome);
	}
	
	public Page<Professor> buscarPorNomeP(String nome, Pageable pageable) {
		return professorRepository.buscarNomeP(nome, pageable);
	}
	
	public List<Professor> buscarTodos(){
		return professorRepository.findAll();
	}
	
	
	public Professor salvarProfessor(@RequestBody Professor professor) throws ProfessorDuplicadoException {
		Professor professorEncontrado = professorRepository.buscarPorMatricula(professor.getMatricula());
		if(professorEncontrado != null) {
			throw new ProfessorDuplicadoException("Professor Duplicado!" + " ID: " +professorEncontrado.getIdProfessor() +
					" Matricula: " + professorEncontrado.getMatricula());
		}
		return professorRepository.save(professor);
	}
	
	public Professor atualizarProfessor(@RequestBody Professor professor) {
		return professorRepository.save(professor);
	}

	public void deletarProfessor(@RequestBody Professor professor) {
	professorRepository.delete(professor);
	}
	
	
}

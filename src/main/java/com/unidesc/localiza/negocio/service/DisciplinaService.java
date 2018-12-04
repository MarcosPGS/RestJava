package com.unidesc.localiza.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.unidesc.localiza.entity.Disciplina;
import com.unidesc.localiza.repository.DisciplinaRepository;

@Service
public class DisciplinaService {

	@Autowired
	DisciplinaRepository disciplinaRepository;
	
	public List<Disciplina> buscarTodasDisciplina(){
		return disciplinaRepository.findAll();
	}
	
	public	Disciplina salvarDisciplina(@RequestBody Disciplina disciplina) {
		return disciplinaRepository.save(disciplina);
	}
	
	
	public Disciplina atualizarDisciplina(@RequestBody Disciplina disciplina) {
		return disciplinaRepository.save(disciplina);
	}

	public void deletarDisciplina(@RequestBody Disciplina disciplina) {
		disciplinaRepository.delete(disciplina);
	}
}

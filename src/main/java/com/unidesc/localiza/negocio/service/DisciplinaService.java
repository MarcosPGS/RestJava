package com.unidesc.localiza.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.unidesc.localiza.entity.Disciplina;
import com.unidesc.localiza.exceptions.DisciplinaDuplicadaExcepton;
import com.unidesc.localiza.repository.DisciplinaRepository;

@Service
public class DisciplinaService {

	@Autowired
	DisciplinaRepository disciplinaRepository;
	
	public List<Disciplina> buscarPorNome(String nome){
		return  disciplinaRepository.buscarPorNomeDisciplina(nome);
		
	}
	
	public Page<Disciplina> buscarPorNomePaginaDisciplina(String nome, Pageable pageable){
		return disciplinaRepository.buscarNomePagina(nome, pageable);
	}
	public List<Disciplina> buscarTodasDisciplina(){
		return disciplinaRepository.findAll();
	}
	
	public	Disciplina salvarDisciplina(@RequestBody Disciplina disciplina) throws DisciplinaDuplicadaExcepton {
		Disciplina disciplinaEncontrada = disciplinaRepository.buscarPorNomeDisciplinaUnico(disciplina.getNome());
		if(disciplinaEncontrada != null) {
			throw new DisciplinaDuplicadaExcepton("Disciplina Duplicada! - " + "ID: "+ disciplinaEncontrada.getIdDisciplina());
		}
		return disciplinaRepository.save(disciplina);
	}
	
	
	public Disciplina atualizarDisciplina(@RequestBody Disciplina disciplina) {
		return disciplinaRepository.save(disciplina);
	}

	public void deletarDisciplina(@RequestBody Disciplina disciplina) {
		disciplinaRepository.delete(disciplina);
	}
}

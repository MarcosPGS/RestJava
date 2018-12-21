package com.unidesc.localiza.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.unidesc.localiza.entity.Semestre;
import com.unidesc.localiza.repository.SemestreRepository;

@Service
public class SemestreService {
	
	@Autowired
	SemestreRepository semestreRepository;
	
	public Semestre buscarPorSemestre(String semestre) {
		return semestreRepository.buscarPorSemestre(semestre);
	}
	
	public List<Semestre> buscarTodosSemestres(){
		return semestreRepository.findAll();
	}
	
	public Semestre criarSemestre(@RequestBody Semestre semestre) {
		return semestreRepository.save(semestre);
	}
	public Semestre atualizarSemestre(@RequestBody Semestre semestre) {
		return semestreRepository.save(semestre);
	}

	public void deletarSemestre(@RequestBody Semestre semestre) {
		semestreRepository.delete(semestre);
	}


}

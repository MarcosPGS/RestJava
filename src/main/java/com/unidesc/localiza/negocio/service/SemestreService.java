package com.unidesc.localiza.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.unidesc.localiza.entity.Semestre;
import com.unidesc.localiza.exceptions.SemestreDuplicadoException;
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
	
	public Semestre salvarSemestre(Semestre semestre) throws SemestreDuplicadoException {
		Semestre semestreEncontrado = semestreRepository.buscarPorSemestre(semestre.getSemestre());
		if(semestreEncontrado != null) {
			throw new SemestreDuplicadoException("Semestre Duplicado! - " + "ID: " + semestreEncontrado.getIdSemestre());
		}
		return semestreRepository.save(semestre);
	}
	public Semestre atualizarSemestre(Semestre semestre) {
		return semestreRepository.save(semestre);
	}

	public void deletarSemestre( Long idSemestre) {
		semestreRepository.deleteById(idSemestre);
	}


}

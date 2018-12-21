package com.unidesc.localiza.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.unidesc.localiza.entity.Curso;
import com.unidesc.localiza.repository.CursoRepository;

@Service
public class CursoService {
	@Autowired
	CursoRepository cursoRepository;
	
	public List<Curso> buscarPorNome(String nome){
		return cursoRepository.buscarPorNomeCurso(nome);
	}
	
	public List<Curso> buscarTodosCurso(){
		return cursoRepository.findAll();
	}
	
	public Curso salvarCurso(@RequestBody Curso curso) {
		return cursoRepository.save(curso);
	}
	
	
	public Curso atualizarCurso(@RequestBody Curso curso) {
		return cursoRepository.save(curso);
	}
	
	public void deletarCurso(@RequestBody Curso curso) {
		cursoRepository.delete(curso);
	}
	
	
	
}

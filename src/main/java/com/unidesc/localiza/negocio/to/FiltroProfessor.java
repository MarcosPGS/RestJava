package com.unidesc.localiza.negocio.to;

import com.unidesc.localiza.entity.Bloco;
import com.unidesc.localiza.entity.Curso;
import com.unidesc.localiza.entity.Disciplina;
import com.unidesc.localiza.entity.Professor;

public class FiltroProfessor {
	
	private Disciplina disciplina;
	private Curso curso;
	private Professor professor;
	private Bloco bloco;
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public Bloco getBloco() {
		return bloco;
	}
	public void setBloco(Bloco bloco) {
		this.bloco = bloco;
	}
	
	
}

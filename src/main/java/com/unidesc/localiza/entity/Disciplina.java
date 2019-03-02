package com.unidesc.localiza.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="disciplina")
public class Disciplina implements Serializable {
	
	private static final long serialVersionUID =1l;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	@Column(name="iddisciplina")
	private Long idDisciplina;
	
	@Column(name="nome")
	private String nome;
	
	@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="disciplina_diasemana", joinColumns= {@JoinColumn(name="iddisciplina")}, inverseJoinColumns = {@JoinColumn(name="iddiasemana")})
	private List<DiaSemana> diaSemanas;
	
	@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="disciplina_curso", joinColumns= {@JoinColumn(name="iddisciplina")}, inverseJoinColumns = {@JoinColumn(name="idcurso")})
	private List<Curso> cursos;

	

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="disciplina_bloco",joinColumns= {@JoinColumn(name="iddisciplina")},inverseJoinColumns= {@JoinColumn(name="idbloco")})
	private List<Bloco> blocos;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="disciplina_sala",joinColumns= {@JoinColumn(name="iddisciplina")},inverseJoinColumns= {@JoinColumn(name="idsala")})
	private List<Sala> salas;

	public Long getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(Long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<DiaSemana> getDiaSemanas() {
		return diaSemanas;
	}

	public void setDiaSemanas(List<DiaSemana> diaSemanas) {
		this.diaSemanas = diaSemanas;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public List<Bloco> getBlocos() {
		return blocos;
	}

	public void setBlocos(List<Bloco> blocos) {
		this.blocos = blocos;
	}

	public List<Sala> getSalas() {
		return salas;
	}

	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}

	
	
	
	

	
}

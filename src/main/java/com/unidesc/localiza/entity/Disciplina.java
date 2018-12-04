package com.unidesc.localiza.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	@Column(name="bloco")
	private String bloco;
	@Column(name="sala")
	private String sala;
	@Column(name="dia_semana")
	private String diaSemana;
	
	@ManyToMany
    @JoinTable(name="disciplina_curso", joinColumns= {@JoinColumn(name="iddisciplina")}, inverseJoinColumns = {@JoinColumn(name="idcurso")})
	private List<Curso> cursos;

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

	public String getBloco() {
		return bloco;
	}

	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	
	

}

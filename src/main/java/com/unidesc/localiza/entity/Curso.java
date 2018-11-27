package com.unidesc.localiza.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="curso")
public class Curso implements Serializable {

	private static final long serialVersionUID=1L;
	
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	
	@Column(name="idcurso")
	private Long idCurso;
	@Column(name="nome")
	private String nome;
	@Column(name="semestre")
	private String semestre;
	@Column(name="turno")
	private String turno;
	
	@Column(name="idprofessor")
	private Long idprofessor;

	@OneToMany(mappedBy="idcurso",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Disciplina> disciplinas;

	public Long getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Long idCurso) {
		this.idCurso = idCurso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public Long getIdprofessor() {
		return idprofessor;
	}

	public void setIdprofessor(Long idprofessor) {
		this.idprofessor = idprofessor;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
	
	
	
}

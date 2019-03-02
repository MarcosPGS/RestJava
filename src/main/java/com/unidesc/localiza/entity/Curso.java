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
import javax.persistence.JoinTable;
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
	
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="curso_semestre", joinColumns = {@JoinColumn(name="idcurso")}, inverseJoinColumns= {@JoinColumn(name="idsemestre")})
	private List<Semestre> semestres;

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="curso_turno", joinColumns= {@JoinColumn(name="idcurso")}, inverseJoinColumns= {@JoinColumn(name="idturno")})
	private List<Turno> turno;

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

	public List<Semestre> getSemestres() {
		return semestres;
	}

	public void setSemestres(List<Semestre> semestres) {
		this.semestres = semestres;
	}

	public List<Turno> getTurno() {
		return turno;
	}

	public void setTurno(List<Turno> turno) {
		this.turno = turno;
	}

	
	
	
	
	
	
	
	
		
	
	
}

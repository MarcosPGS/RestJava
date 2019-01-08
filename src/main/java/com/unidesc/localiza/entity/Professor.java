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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="professor")
public class Professor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	
	@Column(name="idprofessor")
	private	Long idProfessor;
	@Column(name="nome")
	private String nome;
	@Column(name="matricula")
	private String matricula;
	
	
	@ManyToMany
    @JoinTable(name="professor_disciplina", joinColumns= {@JoinColumn(name="idprofessor")},
    inverseJoinColumns = {@JoinColumn(name="iddisciplina")})
	private List<Disciplina> disciplinas;


	public Long getIdProfessor() {
		return idProfessor;
	}


	public void setIdProfessor(Long idProfessor) {
		this.idProfessor = idProfessor;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}


	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}


	
	
	
	
}

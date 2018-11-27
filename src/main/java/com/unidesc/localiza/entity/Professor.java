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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="professor")
public class Professor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	


	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	
	@Column(name="idprofessor")
	private	Long idProfessor;
	@Column(name="nome")
	private String nome;
	@Column(name="login")
	private String login;
	@Column(name="senha")
	private String senha;
	
	@OneToMany(mappedBy="idprofessor",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Curso>cursos;

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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	

	
	 
	
	 
	
	
}

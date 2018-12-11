package com.unidesc.localiza.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="administrador")
public class Administrador implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	
	@Column(name="idadministrador")
	private Long idAdministardor;
	@Column(name="nome")
	private String nome;
	@Column(name="login")
	private String login;
	@Column(name="senha")
	private String senha;
	@Column(name="email")
	private String email;
	
	public Long getIdAdministardor() {
		return idAdministardor;
	}
	public void setIdAdministardor(Long idAdministardor) {
		this.idAdministardor = idAdministardor;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
}

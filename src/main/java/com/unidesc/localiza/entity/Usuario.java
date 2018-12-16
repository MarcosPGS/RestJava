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
import javax.persistence.Table;


@Entity
@Table(name="usuario")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idusuario")
	private Long idUsuario;
	@Column(name="login")
	private String login;
	@Column(name="senha")
	private String senha;

	//lAZY = PERMITE NAO PESAR MEU SISTEMA
	//EAGER = OBRIGA CARREGAR A ENTIDADE RELACIONADA;
	//
	//failed to lazily initialize a collection of role: O LAZY NAO ESTA CARREGANDO OS SEUS RELACIONAMENTO
	//POR ISSO USA O "EAGER" NESTE SENARIO;
	@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="usuario_permissao", joinColumns= {@JoinColumn(name="idusuario")},
    inverseJoinColumns = {@JoinColumn(name="idpermissao")})
	private List<Permissao> permissaos;


	public Long getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
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


	public List<Permissao> getPermissaos() {
		return permissaos;
	}


	public void setPermissaos(List<Permissao> permissaos) {
		this.permissaos = permissaos;
	}
	
	
	
	
}

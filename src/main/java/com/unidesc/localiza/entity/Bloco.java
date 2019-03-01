package com.unidesc.localiza.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bloco")
public class Bloco implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="idbloco")
	private Long idBloco;
	@Column(name="descricao")
	private String descricao;
	
	
	public Long getIdBloco() {
		return idBloco;
	}
	public void setIdBloco(Long idBloco) {
		this.idBloco = idBloco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}

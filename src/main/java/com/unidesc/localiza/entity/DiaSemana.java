package com.unidesc.localiza.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.criteria.Expression;

@Entity
@Table(name="dia_semana")
public class DiaSemana implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="iddiasemana")
	private Long idDiaSemana;
	@Column(name="descricao")
	private String descricao;
	
	public Long getIdDiaSemana() {
		return idDiaSemana;
	}
	public void setIdDiaSemana(Long idDiaSemana) {
		this.idDiaSemana = idDiaSemana;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	

}

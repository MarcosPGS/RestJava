package com.unidesc.localiza.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="turno")
public class Turno implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="idturno")
	private Long idTurno;
	@Column(name="descricao")
	private String descricao;
	public Long getIdTurno() {
		return idTurno;
	}
	public void setIdTurno(Long idTurno) {
		this.idTurno = idTurno;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}

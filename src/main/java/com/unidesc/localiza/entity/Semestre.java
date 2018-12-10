package com.unidesc.localiza.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="semestre")
public class Semestre implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	
	@Column(name="idsemestre")
	private Long idSemestre;
	@Column(name="semestre")
	private String semestre;
	
	public Long getIdSemestre() {
		return idSemestre;
	}
	public void setIdSemestre(Long idSemestre) {
		this.idSemestre = idSemestre;
	}
	public String getSemestre() {
		return semestre;
	}
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	
	
	
	
	
}

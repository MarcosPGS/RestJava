package com.unidesc.localiza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unidesc.localiza.entity.Curso;



public interface CursoRepository extends JpaRepository<Curso, Long>, CursoRepositoryQuery{

}

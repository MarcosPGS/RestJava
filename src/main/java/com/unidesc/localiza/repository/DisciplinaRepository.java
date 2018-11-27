package com.unidesc.localiza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unidesc.localiza.entity.Disciplina;

public interface DisciplinaRepository  extends JpaRepository<Disciplina, Long>, DisciplinaRepositoryQuery{

}

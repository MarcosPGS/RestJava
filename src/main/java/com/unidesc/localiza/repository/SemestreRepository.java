package com.unidesc.localiza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unidesc.localiza.entity.Semestre;

public interface SemestreRepository extends JpaRepository<Semestre, Long>, SemestreRepositoryQuery{

}

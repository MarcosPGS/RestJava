package com.unidesc.localiza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unidesc.localiza.entity.Turno;

public interface TurnoRepository extends JpaRepository<Turno, Long>, TurnoRepositoryQuery {

}

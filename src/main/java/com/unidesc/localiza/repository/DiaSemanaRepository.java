package com.unidesc.localiza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unidesc.localiza.entity.DiaSemana;

public interface DiaSemanaRepository extends JpaRepository<DiaSemana, Long>,DiaSemanaRepositoryQuery{

}

package com.unidesc.localiza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unidesc.localiza.entity.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long>, SalaRepositoryQuery {

}

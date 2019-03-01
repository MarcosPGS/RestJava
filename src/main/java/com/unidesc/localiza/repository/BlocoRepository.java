package com.unidesc.localiza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unidesc.localiza.entity.Bloco;

public interface BlocoRepository extends JpaRepository<Bloco, Long>, BlocoRepositoryQuery {

}

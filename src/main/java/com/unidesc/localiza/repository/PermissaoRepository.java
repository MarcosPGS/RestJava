package com.unidesc.localiza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unidesc.localiza.entity.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long>, PermissaoRepositoryQuery {

 }

package com.unidesc.localiza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unidesc.localiza.entity.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long>, AdministradorRepositoryQuery {

}

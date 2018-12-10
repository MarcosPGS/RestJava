package com.unidesc.localiza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unidesc.localiza.entity.Local;

public interface LocalRepository extends JpaRepository<Local, Long>, LocalRepositoryQuery {

}

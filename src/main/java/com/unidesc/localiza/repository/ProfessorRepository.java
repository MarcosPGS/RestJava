package com.unidesc.localiza.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.unidesc.localiza.entity.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long>, ProfessorRepositoryQuery{

	

	

}

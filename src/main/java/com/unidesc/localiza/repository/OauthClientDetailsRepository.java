package com.unidesc.localiza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unidesc.localiza.entity.OauthClientDetails;

public interface OauthClientDetailsRepository extends JpaRepository<OauthClientDetails, String>, OauthClientDetailsRepositoryQuery{

}

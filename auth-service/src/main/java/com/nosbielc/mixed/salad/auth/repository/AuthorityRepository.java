package com.nosbielc.mixed.salad.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nosbielc.mixed.salad.auth.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String>{
	
	Authority findByName(String name);
	
}
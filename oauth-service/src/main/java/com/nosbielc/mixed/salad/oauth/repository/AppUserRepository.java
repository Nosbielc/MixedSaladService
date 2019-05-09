package com.nosbielc.mixed.salad.oauth.repository;

import com.nosbielc.mixed.salad.oauth.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, String> {

}

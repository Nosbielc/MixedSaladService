package com.nosbielc.mixed.salad.bancocentral.repositories;

import com.nosbielc.mixed.salad.bancocentral.entities.Banco;
import com.nosbielc.mixed.salad.bancocentral.entities.TokenSeguranca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ITokenSegurancaoRepository extends JpaRepository<TokenSeguranca, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT tokenSeguranca from TokenSeguranca tokenSeguranca WHERE tokenSeguranca.banco = :banco")
    Page<TokenSeguranca> findByBancoPageable(@Param("banco") Long banco, Pageable pageable);

    @Transactional(readOnly = true)
    Optional<TokenSeguranca> findByBancoAndStrConta(Banco banco, String strConta);
}

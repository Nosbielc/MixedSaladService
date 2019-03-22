package com.nosbielc.mixed.salad.bancocentral.services;

import com.nosbielc.mixed.salad.bancocentral.entities.Banco;
import com.nosbielc.mixed.salad.bancocentral.entities.TokenSeguranca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface ITokenSegurancaService {

    Page<TokenSeguranca> findAllPageable(PageRequest pageRequest);

    Page<TokenSeguranca> findByBancoPageable(Long banco, PageRequest pageRequest);

    Optional<TokenSeguranca> findById(Long id);

    TokenSeguranca persist(TokenSeguranca tokenSeguranca);

    void remove(TokenSeguranca tokenSeguranca);

    Optional<List<TokenSeguranca>> findAll();

    void remove(Long tokenSegurancaoId);

    Optional<TokenSeguranca> findByBancoAndStrConta(Banco banco, String strConta);

    TokenSeguranca merge(TokenSeguranca tokenSeguranca);


}

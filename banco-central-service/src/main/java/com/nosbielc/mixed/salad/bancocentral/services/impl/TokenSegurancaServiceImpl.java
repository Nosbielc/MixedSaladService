package com.nosbielc.mixed.salad.bancocentral.services.impl;

import com.nosbielc.mixed.salad.bancocentral.entities.Banco;
import com.nosbielc.mixed.salad.bancocentral.entities.TokenSeguranca;
import com.nosbielc.mixed.salad.bancocentral.repositories.ITokenSegurancaoRepository;
import com.nosbielc.mixed.salad.bancocentral.services.ITokenSegurancaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TokenSegurancaServiceImpl implements ITokenSegurancaService {

    @Autowired
    ITokenSegurancaoRepository tokenSegurancaoRepository;

    @Override
    public Page<TokenSeguranca> findAllPageable(PageRequest pageRequest) {
        return this.tokenSegurancaoRepository.findAll(pageRequest);
    }

    @Override
    public Page<TokenSeguranca> findByBancoPageable(Long banco, PageRequest pageRequest) {
        return this.tokenSegurancaoRepository.findByBancoPageable(banco, pageRequest);
    }

    @Override
    public Optional<TokenSeguranca> findById(Long id) {
        return this.tokenSegurancaoRepository.findById(id);
    }

    @Override
    public TokenSeguranca persist(TokenSeguranca tokenSeguranca) {
        return this.tokenSegurancaoRepository.save(tokenSeguranca);
    }

    @Override
    public void remove(TokenSeguranca tokenSeguranca) {
        this.tokenSegurancaoRepository.delete(tokenSeguranca);
    }

    @Override
    public Optional<List<TokenSeguranca>> findAll() {
        return Optional.ofNullable(this.tokenSegurancaoRepository.findAll());
    }

    @Override
    public void remove(Long tokenSegurancaoId) {
        this.tokenSegurancaoRepository.deleteById(tokenSegurancaoId);
    }

    @Override
    public Optional<TokenSeguranca> findByBancoAndStrConta(Banco banco, String strConta) {
        return this.tokenSegurancaoRepository.findByBancoAndStrConta(banco, strConta);
    }

    @Override
    public TokenSeguranca merge(TokenSeguranca tokenSeguranca) {
        return this.tokenSegurancaoRepository.save(tokenSeguranca);
    }
}

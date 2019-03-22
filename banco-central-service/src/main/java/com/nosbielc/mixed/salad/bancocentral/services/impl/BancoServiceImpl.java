package com.nosbielc.mixed.salad.bancocentral.services.impl;

import com.nosbielc.mixed.salad.bancocentral.entities.Banco;
import com.nosbielc.mixed.salad.bancocentral.repositories.IBancoRepository;
import com.nosbielc.mixed.salad.bancocentral.services.IBancoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BancoServiceImpl implements IBancoService {

    private static final Logger log = LoggerFactory.getLogger(BancoServiceImpl.class);

    @Autowired
    private IBancoRepository bancoRepository;

    @Override
    public Page<Banco> findAllPageable(PageRequest pageRequest) {
        return this.bancoRepository.findAll(pageRequest);
    }

    @Override
    public Optional<Banco> findById(Long id) {
        return this.bancoRepository.findById(id);
    }

    @Override
    public Banco persist(Banco banco) {
        return this.bancoRepository.save(banco);
    }

    @Override
    public void remove(Banco banco) {
        this.bancoRepository.delete(banco);
    }

    @Override
    public Optional<List<Banco>> findAll() {
        return Optional.ofNullable(this.bancoRepository.findAll());
    }
}

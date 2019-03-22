package com.nosbielc.mixed.salad.bancocentral.services;

import com.nosbielc.mixed.salad.bancocentral.entities.Banco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface IBancoService {

    Page<Banco> findAllPageable(PageRequest pageRequest);

    Optional<Banco> findById(Long id);

    Banco persist(Banco banco);

    void remove(Banco banco);

    Optional<List<Banco>> findAll();
}

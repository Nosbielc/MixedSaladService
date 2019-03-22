package com.nosbielc.mixed.salad.bancocentral.services;

import com.nosbielc.mixed.salad.bancocentral.entities.Transferencia;
import com.nosbielc.mixed.salad.bancocentral.enums.TransferenciaStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface ITrasnferenciaService {

    Page<Transferencia> findAllPageable(PageRequest pageRequest);

    Page<Transferencia> findByBancoOrigemPageable(Long bancoOrigem, PageRequest pageRequest);

    List<Transferencia> findByBancoOrigem(Long bancoOrigem);

    Page<Transferencia> findByBancoDestinoPageable(Long bancoDestino, PageRequest pageRequest);

    List<Transferencia> findByBancoDestino(Long bancoDestino);

    Page<Transferencia> findByContaDestinoPageable(String contaDestino, PageRequest pageRequest);

    List<Transferencia> findByContaDestino(String contaDestino);

    Page<Transferencia> findByContaOrigemPageable(String contaOrigem, PageRequest pageRequest);

    List<Transferencia> findByContaOrigem(String contaOrigem);

    Optional<Transferencia> findById(Long id);

    Transferencia persist(Transferencia transferencia);

    void remove(Transferencia transferencia);

    Optional<List<Transferencia>> findAll();

    void remove(Long transferenciaId);

    Page<Transferencia> findOneAutenticacao(String autenticacao, PageRequest pageRequest);

    Optional<Transferencia> findByAutenticacao(String autenticacao);

    List<Transferencia> findAllByTransferenciaStatusOrderByIdAsc(TransferenciaStatus transferenciaStatus);

}

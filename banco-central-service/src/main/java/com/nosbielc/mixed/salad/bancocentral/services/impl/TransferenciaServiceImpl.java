package com.nosbielc.mixed.salad.bancocentral.services.impl;

import com.nosbielc.mixed.salad.bancocentral.entities.Transferencia;
import com.nosbielc.mixed.salad.bancocentral.enums.TransferenciaStatus;
import com.nosbielc.mixed.salad.bancocentral.repositories.ITransferenciaRepository;
import com.nosbielc.mixed.salad.bancocentral.services.ITrasnferenciaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransferenciaServiceImpl implements ITrasnferenciaService {

    private static final Logger log = LoggerFactory.getLogger(TransferenciaServiceImpl.class);

    @Autowired
    private ITransferenciaRepository transferenciaRepository;

    @Override
    public Page<Transferencia> findAllPageable(PageRequest pageRequest) {
        return this.transferenciaRepository.findAll(pageRequest);
    }

    @Override
    public Page<Transferencia> findByBancoOrigemPageable(Long bancoOrigem, PageRequest pageRequest) {
        return this.transferenciaRepository.findByBancoOrigemPageable(bancoOrigem, pageRequest);
    }

    @Override
    public List<Transferencia> findByBancoOrigem(Long bancoOrigem) {
        return this.transferenciaRepository.findByBancoOrigem(bancoOrigem);
    }

    @Override
    public Page<Transferencia> findByBancoDestinoPageable(Long bancoDestino, PageRequest pageRequest) {
        return this.transferenciaRepository.findByBancoDestinoPageable(bancoDestino, pageRequest);
    }

    @Override
    public List<Transferencia> findByBancoDestino(Long bancoDestino) {
        return this.transferenciaRepository.findByBancoDestino(bancoDestino);
    }

    @Override
    public Page<Transferencia> findByContaDestinoPageable(String contaDestino, PageRequest pageRequest) {
        return this.transferenciaRepository.findByContaDestinoPageable(contaDestino, pageRequest);
    }

    @Override
    public List<Transferencia> findByContaDestino(String contaDestino) {
        return this.transferenciaRepository.findByContaDestino(contaDestino);
    }

    @Override
    public Page<Transferencia> findByContaOrigemPageable(String contaOrigem, PageRequest pageRequest) {
        return this.transferenciaRepository.findByContaOrigemPageable(contaOrigem, pageRequest);
    }

    @Override
    public List<Transferencia> findByContaOrigem(String contaOrigem) {
        return this.transferenciaRepository.findByContaOrigem(contaOrigem);
    }

    @Override
    public Optional<Transferencia> findById(Long id) {
        return this.transferenciaRepository.findById(id);
    }

    @Override
    public Transferencia persist(Transferencia transferencia) {
        return this.transferenciaRepository.save(transferencia);
    }

    @Override
    public void remove(Transferencia transferencia) {
        this.transferenciaRepository.delete(transferencia);
    }

    @Override
    public Optional<List<Transferencia>> findAll() {
        return Optional.ofNullable(this.transferenciaRepository.findAll());
    }

    @Override
    public void remove(Long transferenciaId) {
        this.transferenciaRepository.deleteById(transferenciaId);
    }

    @Override
    public Page<Transferencia> findOneAutenticacao(String autenticacao, PageRequest pageRequest) {
        return this.transferenciaRepository.findOneAutenticacao(autenticacao, pageRequest);
    }

    @Override
    public Optional<Transferencia> findByAutenticacao(String autenticacao) {
        return this.transferenciaRepository.findByAutenticacao(autenticacao);
    }

    @Override
    public List<Transferencia> findAllByTransferenciaStatusOrderByIdAsc(TransferenciaStatus transferenciaStatus) {
        return this.transferenciaRepository.findAllByTransferenciaStatusOrderByIdAsc(transferenciaStatus);
    }
}

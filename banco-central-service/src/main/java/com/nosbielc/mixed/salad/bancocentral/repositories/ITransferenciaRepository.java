package com.nosbielc.mixed.salad.bancocentral.repositories;

import com.nosbielc.mixed.salad.bancocentral.entities.Transferencia;
import com.nosbielc.mixed.salad.bancocentral.enums.TransferenciaStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ITransferenciaRepository extends JpaRepository<Transferencia, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT transferencia from Transferencia transferencia WHERE transferencia.bancoOrigem = :bancoOrigem")
    Page<Transferencia> findByBancoOrigemPageable(@Param("bancoOrigem") Long bancoOrigem, Pageable pageable);

    @Transactional(readOnly = true)
    @Query("SELECT transferencia from Transferencia transferencia WHERE transferencia.bancoOrigem = :bancoOrigem")
    List<Transferencia> findByBancoOrigem(@Param("bancoOrigem") Long bancoOrigem);

    @Transactional(readOnly = true)
    @Query("SELECT transferencia from Transferencia transferencia WHERE transferencia.bancoDestino = :bancoDestino")
    Page<Transferencia> findByBancoDestinoPageable(@Param("bancoDestino") Long bancoDestino, Pageable pageable);

    @Transactional(readOnly = true)
    @Query("SELECT transferencia from Transferencia transferencia WHERE transferencia.bancoDestino = :bancoDestino")
    List<Transferencia> findByBancoDestino(@Param("bancoDestino") Long bancoDestino);

    @Transactional(readOnly = true)
    @Query("SELECT transferencia from Transferencia transferencia WHERE transferencia.contaDestino = :contaDestino")
    Page<Transferencia> findByContaDestinoPageable(@Param("contaDestino") String contaDestino, Pageable pageable);

    @Transactional(readOnly = true)
    @Query("SELECT transferencia from Transferencia transferencia WHERE transferencia.contaDestino = :contaDestino")
    List<Transferencia> findByContaDestino(@Param("contaDestino") String contaDestino);

    @Transactional(readOnly = true)
    @Query("SELECT transferencia from Transferencia transferencia WHERE transferencia.contaOrigem = :contaOrigem")
    Page<Transferencia> findByContaOrigemPageable(@Param("contaOrigem") String contaOrigem, Pageable pageable);

    @Transactional(readOnly = true)
    @Query("SELECT transferencia from Transferencia transferencia WHERE transferencia.contaOrigem = :contaOrigem")
    List<Transferencia> findByContaOrigem(@Param("contaOrigem") String contaOrigem);

    @Transactional(readOnly = true)
    @Query("SELECT transferencia from Transferencia transferencia WHERE transferencia.autenticacao = :autenticacao")
    Page<Transferencia> findOneAutenticacao(@Param("autenticacao") String autenticacao, Pageable pageable);

    @Transactional(readOnly = true)
    Optional<Transferencia> findByAutenticacao(String autenticacao);

    @Transactional(readOnly = true)
    List<Transferencia> findAllByTransferenciaStatusOrderByIdAsc(TransferenciaStatus transferenciaStatus);

}

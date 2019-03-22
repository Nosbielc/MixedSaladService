package com.nosbielc.mixed.salad.bancocentral.repositories;

import com.nosbielc.mixed.salad.bancocentral.entities.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBancoRepository extends JpaRepository<Banco, Long> {
}

package com.nosbielc.mixed.salad.bancocentral.client.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.StringJoiner;

@Getter
@Setter
public class DenisDeposito implements Serializable {

    private String cpf;

    private Double valor;

    public DenisDeposito(String cpf, Double valor) {
        this.cpf = cpf;
        this.valor = valor;
    }

    public DenisDeposito() {
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DenisDeposito.class.getSimpleName() + "[", "]")
                .add("cpf='" + cpf + "'")
                .add("valor=" + valor)
                .toString();
    }

}

package com.nosbielc.mixed.salad.bancocentral.client.dto;

import java.io.Serializable;
import java.util.StringJoiner;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}

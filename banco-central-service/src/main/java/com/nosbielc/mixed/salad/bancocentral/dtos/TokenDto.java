package com.nosbielc.mixed.salad.bancocentral.dtos;

import com.nosbielc.mixed.salad.bancocentral.entities.Banco;

import java.io.Serializable;

public class TokenDto implements Serializable {

    private Long id;

    private String strToken;

    private String strConta;

    private Banco banco;

    public TokenDto(Long id, String strToken, String strConta) {
        this.id = id;
        this.strToken = strToken;
        this.strConta = strConta;
    }

    public TokenDto(Long id, String strToken, String strConta, Banco banco) {
        this.id = id;
        this.strToken = strToken;
        this.strConta = strConta;
        this.banco = banco;
    }

    public TokenDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStrToken() {
        return strToken;
    }

    public void setStrToken(String strToken) {
        this.strToken = strToken;
    }

    public String getStrConta() {
        return strConta;
    }

    public void setStrConta(String strConta) {
        this.strConta = strConta;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }
}

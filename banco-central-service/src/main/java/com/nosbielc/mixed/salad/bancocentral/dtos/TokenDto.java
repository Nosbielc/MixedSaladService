package com.nosbielc.mixed.salad.bancocentral.dtos;

import com.nosbielc.mixed.salad.bancocentral.entities.Banco;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
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

}

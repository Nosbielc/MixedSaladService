package com.nosbielc.mixed.salad.bancocentral.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.StringJoiner;

@Getter
@Setter
@Entity
@Table(name = "token_seguranca")
public class TokenSeguranca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "str_token", nullable = false)
    private String strToken;

    @Column(name = "str_conta", nullable = false)
    private String strConta;

    @ManyToOne
    private Banco banco;

    public TokenSeguranca() {
    }

    public TokenSeguranca(String strToken, String strConta, Banco banco) {
        this.strToken = strToken;
        this.strConta = strConta;
        this.banco = banco;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TokenSeguranca.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("strToken='" + strToken + "'")
                .add("strConta='" + strConta + "'")
                .add("banco=" + banco)
                .toString();
    }
}

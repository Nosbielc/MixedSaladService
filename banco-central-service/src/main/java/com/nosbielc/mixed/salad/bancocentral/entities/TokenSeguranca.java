package com.nosbielc.mixed.salad.bancocentral.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.StringJoiner;

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

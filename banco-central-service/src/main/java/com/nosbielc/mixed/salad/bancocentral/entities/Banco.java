package com.nosbielc.mixed.salad.bancocentral.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Entity
@Table(name = "banco")
public class Banco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cod_banco", nullable = false)
    private Long codBanco;

    @Column(name = "str_nome_base", nullable = false)
    private String strNomeBase;

    @Column(name = "str_nome", nullable = false)
    private String strNome;

    @Column(name = "is_ativo", nullable = false)
    private Boolean ativo;

    @OneToMany(mappedBy = "bancoOrigem", cascade = CascadeType.ALL)
    private List<Transferencia> transferenciaOrigem = new ArrayList<>();

    @OneToMany(mappedBy = "bancoDestino", cascade = CascadeType.ALL)
    private List<Transferencia> transferenciaDestino = new ArrayList<>();

    @OneToMany(mappedBy = "banco", cascade = CascadeType.ALL)
    private List<TokenSeguranca> tokenSeguranca = new ArrayList<>();

    public Banco() {
    }

    public Banco(Long codBanco, String strNomeBase, String strNome, Boolean ativo) {
        this.codBanco = codBanco;
        this.strNomeBase = strNomeBase;
        this.strNome = strNome;
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Banco.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("codBanco=" + codBanco)
                .add("strNomeBase='" + strNomeBase + "'")
                .add("strNome='" + strNome + "'")
                .add("ativo=" + ativo)
                .add("transferenciaOrigem=" + transferenciaOrigem)
                .add("transferenciaDestino=" + transferenciaDestino)
                .toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodBanco() {
        return codBanco;
    }

    public void setCodBanco(Long codBanco) {
        this.codBanco = codBanco;
    }

    public String getStrNomeBase() {
        return strNomeBase;
    }

    public void setStrNomeBase(String strNomeBase) {
        this.strNomeBase = strNomeBase;
    }

    public String getStrNome() {
        return strNome;
    }

    public void setStrNome(String strNome) {
        this.strNome = strNome;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public List<Transferencia> getTransferenciaOrigem() {
        return transferenciaOrigem;
    }

    public void setTransferenciaOrigem(List<Transferencia> transferenciaOrigem) {
        this.transferenciaOrigem = transferenciaOrigem;
    }

    public List<Transferencia> getTransferenciaDestino() {
        return transferenciaDestino;
    }

    public void setTransferenciaDestino(List<Transferencia> transferenciaDestino) {
        this.transferenciaDestino = transferenciaDestino;
    }

    public List<TokenSeguranca> getTokenSeguranca() {
        return tokenSeguranca;
    }

    public void setTokenSeguranca(List<TokenSeguranca> tokenSeguranca) {
        this.tokenSeguranca = tokenSeguranca;
    }
}

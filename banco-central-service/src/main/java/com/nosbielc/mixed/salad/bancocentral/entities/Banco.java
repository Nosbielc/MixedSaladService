package com.nosbielc.mixed.salad.bancocentral.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Getter
@Setter
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

}

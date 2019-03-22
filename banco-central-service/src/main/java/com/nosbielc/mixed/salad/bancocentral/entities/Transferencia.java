package com.nosbielc.mixed.salad.bancocentral.entities;

import com.nosbielc.mixed.salad.bancocentral.enums.TransferenciaStatus;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.StringJoiner;

@Entity
@Table(name = "transferencia")
public class Transferencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "str_autenticacao", nullable = false)
    private String autenticacao;

    @Column(name = "vlr_transferencia", nullable = false, precision = 2)
    private Float valorTransferencia;

    @Column(name = "dt_transferencia", nullable = false)
    @CreatedBy
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTimeTransferencia;

    @ManyToOne
    private Banco bancoOrigem;

    @Column(name = "str_conta_origem", nullable = false)
    private String contaOrigem;

    @Enumerated(EnumType.ORDINAL)
    private TransferenciaStatus transferenciaStatus;

    @ManyToOne
    private Banco bancoDestino;

    @Column(name = "str_conta_destino", nullable = false)
    private String contaDestino;

    public Transferencia(String autenticacao, Float valorTransferencia,
                         Banco bancoOrigem, String contaOrigem, TransferenciaStatus transferenciaStatus,
                         Banco bancoDestino, String contaDestino, Date dateTimeTransferencia) {
        this.autenticacao = autenticacao;
        this.valorTransferencia = valorTransferencia;
        this.bancoOrigem = bancoOrigem;
        this.contaOrigem = contaOrigem;
        this.transferenciaStatus = transferenciaStatus;
        this.bancoDestino = bancoDestino;
        this.contaDestino = contaDestino;
        this.dateTimeTransferencia = dateTimeTransferencia;
    }

    public Transferencia() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutenticacao() {
        return autenticacao;
    }

    public void setAutenticacao(String autenticacao) {
        this.autenticacao = autenticacao;
    }

    public Float getValorTransferencia() {
        return valorTransferencia;
    }

    public void setValorTransferencia(Float valorTransferencia) {
        this.valorTransferencia = valorTransferencia;
    }

    public Date getDateTimeTransferencia() {
        return dateTimeTransferencia;
    }

    public void setDateTimeTransferencia(Date dateTimeTransferencia) {
        this.dateTimeTransferencia = dateTimeTransferencia;
    }

    public Banco getBancoOrigem() {
        return bancoOrigem;
    }

    public void setBancoOrigem(Banco bancoOrigem) {
        this.bancoOrigem = bancoOrigem;
    }

    public String getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(String contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public TransferenciaStatus getTransferenciaStatus() {
        return transferenciaStatus;
    }

    public void setTransferenciaStatus(TransferenciaStatus transferenciaStatus) {
        this.transferenciaStatus = transferenciaStatus;
    }

    public Banco getBancoDestino() {
        return bancoDestino;
    }

    public void setBancoDestino(Banco bancoDestino) {
        this.bancoDestino = bancoDestino;
    }

    public String getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(String contaDestino) {
        this.contaDestino = contaDestino;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Transferencia.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("autenticacao='" + autenticacao + "'")
                .add("valorTransferencia=" + valorTransferencia)
                .add("dateTimeTransferencia=" + dateTimeTransferencia)
                .add("bancoOrigem=" + bancoOrigem)
                .add("contaOrigem='" + contaOrigem + "'")
                .add("transferenciaStatus=" + transferenciaStatus)
                .add("bancoDestino=" + bancoDestino)
                .add("contaDestino='" + contaDestino + "'")
                .toString();
    }
}

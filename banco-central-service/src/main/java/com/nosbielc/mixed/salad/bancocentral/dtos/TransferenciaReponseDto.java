package com.nosbielc.mixed.salad.bancocentral.dtos;

import com.nosbielc.mixed.salad.bancocentral.entities.Transferencia;
import com.nosbielc.mixed.salad.bancocentral.enums.TransferenciaStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.StringJoiner;

public class TransferenciaReponseDto implements Serializable {

    private Long id;
    private String autenticacao;
    private Float valorTransferencia;
    private Date dateTimeTransferencia;
    private BancoDto bancoOrigem;
    private String contaOrigem;
    private TransferenciaStatus transferenciaStatus;
    private BancoDto bancoDestino;
    private String contaDestino;

    /**
     *
     * @param id
     * @param autenticacao
     * @param valorTransferencia
     * @param dateTimeTransferencia
     * @param bancoOrigem
     * @param contaOrigem
     * @param transferenciaStatus
     * @param bancoDestino
     * @param contaDestino
     */
    public TransferenciaReponseDto(Long id, String autenticacao, Float valorTransferencia, Date dateTimeTransferencia,
                                   BancoDto bancoOrigem, String contaOrigem, TransferenciaStatus transferenciaStatus,
                                   BancoDto bancoDestino, String contaDestino) {
        this.id = id;
        this.autenticacao = autenticacao;
        this.valorTransferencia = valorTransferencia;
        this.dateTimeTransferencia = dateTimeTransferencia;
        this.bancoOrigem = bancoOrigem;
        this.contaOrigem = contaOrigem;
        this.transferenciaStatus = transferenciaStatus;
        this.bancoDestino = bancoDestino;
        this.contaDestino = contaDestino;
    }

    /**
     *
     * @param transferencia
     */
    public TransferenciaReponseDto(Transferencia transferencia) {
        this.id = transferencia.getId();
        this.autenticacao = transferencia.getAutenticacao();
        this.valorTransferencia = transferencia.getValorTransferencia();
        this.dateTimeTransferencia = transferencia.getDateTimeTransferencia();
        this.bancoOrigem = new BancoDto(transferencia.getBancoOrigem());
        this.contaOrigem = transferencia.getContaOrigem();
        this.transferenciaStatus = transferencia.getTransferenciaStatus();
        this.bancoDestino = new BancoDto(transferencia.getBancoDestino());;
        this.contaDestino = transferencia.getContaDestino();
    }

    public TransferenciaReponseDto() {
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TransferenciaReponseDto.class.getSimpleName() + "[", "]")
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

    public BancoDto getBancoOrigem() {
        return bancoOrigem;
    }

    public void setBancoOrigem(BancoDto bancoOrigem) {
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

    public BancoDto getBancoDestino() {
        return bancoDestino;
    }

    public void setBancoDestino(BancoDto bancoDestino) {
        this.bancoDestino = bancoDestino;
    }

    public String getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(String contaDestino) {
        this.contaDestino = contaDestino;
    }
}

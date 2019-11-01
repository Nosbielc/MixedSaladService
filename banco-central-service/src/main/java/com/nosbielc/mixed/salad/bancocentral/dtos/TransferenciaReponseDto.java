package com.nosbielc.mixed.salad.bancocentral.dtos;

import com.nosbielc.mixed.salad.bancocentral.entities.Transferencia;
import com.nosbielc.mixed.salad.bancocentral.enums.TransferenciaStatus;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.StringJoiner;

@Getter
@Setter
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
        this.bancoDestino = new BancoDto(transferencia.getBancoDestino());
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
}

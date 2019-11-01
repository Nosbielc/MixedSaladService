package com.nosbielc.mixed.salad.bancocentral.entities;

import com.nosbielc.mixed.salad.bancocentral.enums.TransferenciaStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.StringJoiner;

@Getter
@Setter
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

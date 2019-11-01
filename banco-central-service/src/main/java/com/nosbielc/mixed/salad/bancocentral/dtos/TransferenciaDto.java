package com.nosbielc.mixed.salad.bancocentral.dtos;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.StringJoiner;

@Getter
@Setter
public class TransferenciaDto implements Serializable {

    @NotEmpty(message = "valorTransferencia não pode ser null.")
    private String valorTransferencia;

    @NotNull(message = "Banco de Origem não pode ser null.")
    private Long bancoOrigem;

    @NotEmpty(message = "contaOrigem não pode ser null.")
    @Length(min = 11, max = 14, message = "contaOrigem pode ter no minimo 11 e maximo 14 caracteres.")
    private String contaOrigem;

    @NotNull(message = "Banco de Destino não pode ser null.")
    private Long bancoDestino;

    @NotEmpty(message = "contaDestino não pode ser null.")
    @Length(min = 11, max = 14, message = "contaDestino pode ter no minimo 11 e maximo 14 caracteres.")
    private String contaDestino;

    @NotNull(message = "code da transferencia não pode ser null.")
    public Long code;

    public TransferenciaDto() {
        this.bancoOrigem = Long.MIN_VALUE;
        this.bancoDestino = Long.MIN_VALUE;
        this.code = Long.MIN_VALUE;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TransferenciaDto.class.getSimpleName() + "[", "]")
                .add("valorTransferencia='" + valorTransferencia + "'")
                .add("bancoOrigem=" + bancoOrigem)
                .add("contaOrigem='" + contaOrigem + "'")
                .add("bancoDestino=" + bancoDestino)
                .add("contaDestino='" + contaDestino + "'")
                .add("code=" + code)
                .toString();
    }
}

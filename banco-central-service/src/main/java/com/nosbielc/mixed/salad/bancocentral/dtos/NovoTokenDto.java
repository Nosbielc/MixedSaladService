package com.nosbielc.mixed.salad.bancocentral.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.StringJoiner;

public class NovoTokenDto implements Serializable {

    @NotEmpty(message = "strConta não pode ser null.")
    @Length(min = 11, max = 14, message = "strConta pode ter no minimo 11 e maximo 14 caracteres.")
    private String strConta;

    @NotNull(message = "bancoId não pode ser null.")
    private Long bancoId;

    public NovoTokenDto(String strConta, Long bancoId) {
        this.strConta = strConta;
        this.bancoId = bancoId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", NovoTokenDto.class.getSimpleName() + "[", "]")
                .add("strConta='" + strConta + "'")
                .add("bancoId=" + bancoId)
                .toString();
    }

    public String getStrConta() {
        return strConta;
    }

    public void setStrConta(String strConta) {
        this.strConta = strConta;
    }

    public Long getBancoId() {
        return bancoId;
    }

    public void setBancoId(Long bancoId) {
        this.bancoId = bancoId;
    }
}

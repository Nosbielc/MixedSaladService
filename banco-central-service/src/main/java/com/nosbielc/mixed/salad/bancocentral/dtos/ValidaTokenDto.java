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
public class ValidaTokenDto implements Serializable {

    @NotEmpty(message = "strConta não pode ser null.")
    @Length(min = 11, max = 14, message = "strConta pode ter no minimo 11 e maximo 14 caracteres.")
    private String strConta;

    @NotNull(message = "bancoId não pode ser null.")
    private Long bancoId;

    @NotNull(message = "code não pode ser null.")
    private Long code;

    public ValidaTokenDto(String strConta, Long bancoId,
                          Long code) {
        this.strConta = strConta;
        this.bancoId = bancoId;
        this.code = code;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ValidaTokenDto.class.getSimpleName() + "[", "]")
                .add("strConta='" + strConta + "'")
                .add("bancoId=" + bancoId)
                .add("code=" + code)
                .toString();
    }

}

package com.nosbielc.mixed.salad.bancocentral.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.StringJoiner;

public class NovoBancoDto implements Serializable {

    @NotNull(message = "CodBanco não pode ser null.")
    private Long codBanco;

    @NotEmpty(message = "strNomeBase não pode ser null.")
    @Length(min = 5, max = 10, message = "strNomeBase pode ter no minimo 5 e maximo 10 caracteres.")
    private String strNomeBase;

    @NotEmpty(message = "strNome não pode ser null.")
    @Length(min = 5, max = 200, message = "strNome pode ter no minimo 5 e maximo 10 caracteres.")
    private String strNome;

    public NovoBancoDto() {
        this.codBanco = Long.MIN_VALUE;
    }

    public NovoBancoDto(Long codBanco, String strNomeBase,
                        String strNome) {
        this.codBanco = codBanco;
        this.strNomeBase = strNomeBase;
        this.strNome = strNome;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", NovoBancoDto.class.getSimpleName() + "[", "]")
                .add("codBanco=" + codBanco)
                .add("strNomeBase='" + strNomeBase + "'")
                .add("strNome='" + strNome + "'")
                .toString();
    }
}

package com.nosbielc.mixed.salad.bancocentral.dtos;

import com.nosbielc.mixed.salad.bancocentral.entities.Banco;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.StringJoiner;

public class BancoDto implements Serializable {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @NotNull(message = "CodBanco não pode ser null.")
    private Long codBanco;

    @Getter
    @Setter
    @NotEmpty(message = "strNomeBase não pode ser null.")
    @Length(min = 5, max = 10, message = "strNomeBase pode ter no minimo 5 e maximo 10 caracteres.")
    private String strNomeBase;

    @Getter
    @Setter
    @NotEmpty(message = "strNome não pode ser null.")
    @Length(min = 5, max = 200, message = "strNome pode ter no minimo 5 e maximo 10 caracteres.")
    private String strNome;

    @Getter
    @Setter
    private Boolean ativo;

    public BancoDto() {
        this.codBanco = Long.MIN_VALUE;
    }

    public BancoDto(Long codBanco,
                    String strNomeBase,
                    String strNome, Boolean ativo) {
        this.codBanco = codBanco;
        this.strNomeBase = strNomeBase;
        this.strNome = strNome;
        this.ativo = ativo;
    }

    public BancoDto(Banco banco) {
        this.id = banco.getId();
        this.codBanco = banco.getCodBanco();
        this.strNomeBase = banco.getStrNomeBase();
        this.strNome = banco.getStrNome();
        this.ativo = banco.getAtivo();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BancoDto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("codBanco=" + codBanco)
                .add("strNomeBase='" + strNomeBase + "'")
                .add("strNome='" + strNome + "'")
                .add("ativo=" + ativo)
                .toString();
    }

}

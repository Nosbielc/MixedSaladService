package com.nosbielc.mixed.salad.bancocentral.controllers.utils;

import com.nosbielc.mixed.salad.bancocentral.dtos.BancoDto;
import com.nosbielc.mixed.salad.bancocentral.dtos.NovoBancoDto;
import com.nosbielc.mixed.salad.bancocentral.entities.Banco;
import com.nosbielc.mixed.salad.bancocentral.response.Content;

public class BancoControllerUtils {

    /**
     * Converts a Banco entity to its respective DTO.
     *
     * @param banco
     * @return BancoDto
     */
    public Content<BancoDto> toContentBancoDto(Banco banco) {
        Content<BancoDto> content = new Content<>();
        content.setObjects(toBancoDto(banco));
        return content;
    }

    public BancoDto toBancoDto(Banco banco) {
        BancoDto bancoDto = new BancoDto();
        bancoDto.setId(banco.getId());
        bancoDto.setStrNomeBase(banco.getStrNomeBase());
        bancoDto.setStrNome(banco.getStrNome());
        bancoDto.setCodBanco(banco.getCodBanco());
        bancoDto.setAtivo(banco.getAtivo());
        return bancoDto;
    }

    public Banco toBanco(NovoBancoDto novoBancoDto) {
        return new Banco(novoBancoDto.getCodBanco(), novoBancoDto.getStrNomeBase(), novoBancoDto.getStrNome(), Boolean.TRUE);
    }
}

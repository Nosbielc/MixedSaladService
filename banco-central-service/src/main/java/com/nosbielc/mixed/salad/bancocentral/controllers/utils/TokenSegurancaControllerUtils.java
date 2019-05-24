package com.nosbielc.mixed.salad.bancocentral.controllers.utils;

import com.nosbielc.mixed.salad.bancocentral.dtos.NovoTokenDto;
import com.nosbielc.mixed.salad.bancocentral.dtos.TokenDto;
import com.nosbielc.mixed.salad.bancocentral.dtos.ValidaTokenDto;
import com.nosbielc.mixed.salad.bancocentral.entities.Banco;
import com.nosbielc.mixed.salad.bancocentral.entities.TokenSeguranca;
import com.nosbielc.mixed.salad.bancocentral.response.Content;
import com.nosbielc.mixed.salad.bancocentral.response.Response;
import com.nosbielc.mixed.salad.bancocentral.services.impl.BancoServiceImpl;
import com.nosbielc.mixed.salad.bancocentral.services.impl.TokenSegurancaServiceImpl;
import com.nosbielc.mixed.salad.bancocentral.utils.SecretKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TokenSegurancaControllerUtils {

    @Autowired
    private BancoServiceImpl bancoService;

    @Autowired
    private TokenSegurancaServiceImpl tokenSegurancaService;

    public Response<Content<TokenDto>> criarToken(NovoTokenDto novoTokenDto){
        Response<Content<TokenDto>> response = new Response<>();

        Optional<Banco> banco = bancoService.findById(novoTokenDto.getBancoId());

        if (banco.isPresent()) {

            //Gerando Token para a conta
            Optional<TokenSeguranca> tokenSegurancaAntigo =
                    this.tokenSegurancaService.findByBancoAndStrConta(banco.get(), novoTokenDto.getStrConta());
            TokenSeguranca tokenSeguranca = null;

            if (!tokenSegurancaAntigo.isPresent()) {
                tokenSeguranca = this.tokenSegurancaService.persist(new TokenSeguranca(
                        SecretKeyUtils.getKey(),
                        novoTokenDto.getStrConta(),
                        banco.get()));
            } else {
                tokenSegurancaAntigo.get().setStrToken(SecretKeyUtils.getKey());
                tokenSeguranca = this.tokenSegurancaService.merge(tokenSegurancaAntigo.get());
            }
                response.setData(toContentTokenDto(tokenSeguranca));
        } else {
            response.addError("Banco informado não esta cadastrado na base do banco central");
        }

        return response;
    }

    public Content<TokenDto> toContentTokenDto(TokenSeguranca tokenSeguranca) {
        Content<TokenDto> content = new Content<>();
        content.setContent(new TokenDto(
                tokenSeguranca.getId(), tokenSeguranca.getStrToken(),
                tokenSeguranca.getStrConta()));
        return content;
    }

    public Response<String> validarToken(ValidaTokenDto validaTokenDto) {
        Response<String> response = new Response<>();

        Optional<Banco> banco = bancoService.findById(validaTokenDto.getBancoId());

        if (banco.isPresent()) {

            Optional<TokenSeguranca> tokenSeguranca =
                    this.tokenSegurancaService.findByBancoAndStrConta(banco.get(), validaTokenDto.getStrConta());

            if (tokenSeguranca.isPresent()) {

                if (SecretKeyUtils.isCodeValid(tokenSeguranca.get().getStrToken(), validaTokenDto.getCode().intValue())) {
                    response.setData("Codigo inserido é valido e foi gerado de uma semente segura. :)");
                } else {
                    response.setData("Codigo inserido é invalido, solicite um novo token ou tente novamente. :(");
                }

            } else {
                response.setData("Erro ao buscar Token na base dados. :(");
            }

        } else {
            response.addError("Banco informado não esta cadastrado na base do banco central");
        }

        return response;
    }

}

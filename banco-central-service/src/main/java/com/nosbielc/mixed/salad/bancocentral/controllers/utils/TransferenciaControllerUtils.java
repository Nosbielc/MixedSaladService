package com.nosbielc.mixed.salad.bancocentral.controllers.utils;

import com.nosbielc.mixed.salad.bancocentral.dtos.BancoDto;
import com.nosbielc.mixed.salad.bancocentral.dtos.TransferenciaDto;
import com.nosbielc.mixed.salad.bancocentral.dtos.TransferenciaReponseDto;
import com.nosbielc.mixed.salad.bancocentral.entities.Banco;
import com.nosbielc.mixed.salad.bancocentral.entities.TokenSeguranca;
import com.nosbielc.mixed.salad.bancocentral.entities.Transferencia;
import com.nosbielc.mixed.salad.bancocentral.enums.TransferenciaStatus;
import com.nosbielc.mixed.salad.bancocentral.response.Content;
import com.nosbielc.mixed.salad.bancocentral.response.Response;
import com.nosbielc.mixed.salad.bancocentral.services.impl.BancoServiceImpl;
import com.nosbielc.mixed.salad.bancocentral.services.impl.TokenSegurancaServiceImpl;
import com.nosbielc.mixed.salad.bancocentral.services.impl.TransferenciaServiceImpl;
import com.nosbielc.mixed.salad.bancocentral.utils.Monetario;
import com.nosbielc.mixed.salad.bancocentral.utils.SecretKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Optional;

public class TransferenciaControllerUtils {

    @Autowired
    private BancoServiceImpl bancoService;

    @Autowired
    private TokenSegurancaServiceImpl tokenSegurancaService;

    @Autowired
    private TransferenciaServiceImpl transferenciaService;

    public static Content<TransferenciaReponseDto> toContentTransferenciaResponseDto(Transferencia transferencia) {
        Content<TransferenciaReponseDto> content = new Content<>();
        content.setContent(toTransferenciaResponseDto(transferencia));
        return content;
    }

    public static TransferenciaReponseDto toTransferenciaResponseDto(Transferencia transferencia) {
        return new TransferenciaReponseDto(transferencia.getId(), transferencia.getAutenticacao(),
                transferencia.getValorTransferencia(),
                transferencia.getDateTimeTransferencia(),
                new BancoDto(transferencia.getBancoOrigem()), transferencia.getContaOrigem(),
                transferencia.getTransferenciaStatus(),
                new BancoDto(transferencia.getBancoDestino()), transferencia.getContaDestino());
    }

    public Response<Content<TransferenciaReponseDto>> criaTransferencia(TransferenciaDto transferenciaDto) throws Exception {
        Response<Content<TransferenciaReponseDto>> response = new Response<>();

        Optional<Banco> bancoOrigem = bancoService.findById(transferenciaDto.getBancoOrigem());
        Optional<Banco> bancoDestino = bancoService.findById(transferenciaDto.getBancoDestino());

        if (bancoOrigem.isPresent()) {
            if (bancoDestino.isPresent()) {

                Float valor = Monetario.converteToBig(transferenciaDto.getValorTransferencia()).floatValue();

                if (valor > 0 ) {

                    Optional<TokenSeguranca> tokenSeguranca =
                            this.tokenSegurancaService.findByBancoAndStrConta(bancoOrigem.get(), transferenciaDto.getContaOrigem());

                    if (SecretKeyUtils.isCodeValid(tokenSeguranca.get().getStrToken(),
                            transferenciaDto.getCode().intValue()) || Boolean.TRUE) {
                        String hashIdTransferencia =
                                SecretKeyUtils.getHashTransferencia(
                                        transferenciaDto.getContaOrigem(),
                                        transferenciaDto.getCode().toString(),
                                        transferenciaDto.getContaDestino(),
                                        String.valueOf(System.currentTimeMillis()));

                        Transferencia transferencia = this.transferenciaService.persist(new Transferencia(
                                hashIdTransferencia,
                                valor,
                                bancoOrigem.get(),
                                transferenciaDto.getContaOrigem(),
                                TransferenciaStatus.REQUESTED,
                                        bancoDestino.get(),
                                transferenciaDto.getContaDestino(),
                                new Date()
                                ));
                        response.getData().setContent(new TransferenciaReponseDto(transferencia));
                    } else {
                        response.addError("Sua chave não é valida. :p");
                    }

                } else {
                    response.addError("O valor da transferencia não pode ser menos que 0. :p");
                }

            } else {
                response.addError("Banco de destino informado não esta cadastrado na base do banco central");
            }

        } else {
            response.addError("Banco de origem informado não esta cadastrado na base do banco central");
        }

        return response;
    }

}
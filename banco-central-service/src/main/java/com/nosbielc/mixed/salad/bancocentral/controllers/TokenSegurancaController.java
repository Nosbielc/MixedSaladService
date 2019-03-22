package com.nosbielc.mixed.salad.bancocentral.controllers;

import com.nosbielc.mixed.salad.bancocentral.controllers.utils.ITokenSegurancaController;
import com.nosbielc.mixed.salad.bancocentral.controllers.utils.TokenSegurancaControllerUtils;
import com.nosbielc.mixed.salad.bancocentral.dtos.NovoTokenDto;
import com.nosbielc.mixed.salad.bancocentral.dtos.TokenDto;
import com.nosbielc.mixed.salad.bancocentral.dtos.ValidaTokenDto;
import com.nosbielc.mixed.salad.bancocentral.response.Content;
import com.nosbielc.mixed.salad.bancocentral.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/token")
@CrossOrigin(origins = "*")
public class TokenSegurancaController extends TokenSegurancaControllerUtils implements ITokenSegurancaController {

    private static final Logger log = LoggerFactory.getLogger(TokenSegurancaController.class);

    @Value("${pagination.qtd_by_page}")
    private int fetchForPage;

    @Override
    @PostMapping
    public ResponseEntity<Response<Content<TokenDto>>> gerarToken(@Valid NovoTokenDto novoTokenDto, BindingResult result) throws Exception {
        log.info("Registrando o Token: {}", novoTokenDto.toString());
        return ResponseEntity.ok(criarToken(novoTokenDto));
    }

    @Override
    @PostMapping("/validar")
    public ResponseEntity<Response<String>> validarToken(@Valid ValidaTokenDto validaTokenDto, BindingResult result) throws Exception {
        log.info("Validando o Token: {}", validaTokenDto.toString());
        return ResponseEntity.ok(validarToken(validaTokenDto));
    }
}

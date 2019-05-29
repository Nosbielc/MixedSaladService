package com.nosbielc.mixed.salad.bancocentral.controllers.utils;

import com.nosbielc.mixed.salad.bancocentral.response.Content;
import com.nosbielc.mixed.salad.bancocentral.response.Response;
import com.nosbielc.mixed.salad.bancocentral.dtos.NovoTokenDto;
import com.nosbielc.mixed.salad.bancocentral.dtos.TokenDto;
import com.nosbielc.mixed.salad.bancocentral.dtos.ValidaTokenDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface ITokenSegurancaController {

    @ApiOperation(value = "Geração de Token", nickname = "gerarToken", notes = "Metodo para gerar Token do conta para o banco,")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", responseContainer = "Object", response = TokenDto.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    ResponseEntity<Response<Content<TokenDto>>> gerarToken(@Valid @RequestBody NovoTokenDto novoTokenDto,
                                                           BindingResult result);

    @ApiOperation(value = "Validação do Token", nickname = "validarToken", notes = "Metodo para validar Token da conta para o banco,")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", responseContainer = "Object", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    ResponseEntity<Response<String>> validarToken(@Valid @RequestBody ValidaTokenDto validaTokenDto,
                                                  BindingResult result);
}

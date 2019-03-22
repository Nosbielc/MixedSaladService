package com.nosbielc.mixed.salad.bancocentral.controllers.utils;

import com.nosbielc.mixed.salad.bancocentral.dtos.TransferenciaDto;
import com.nosbielc.mixed.salad.bancocentral.dtos.TransferenciaReponseDto;
import com.nosbielc.mixed.salad.bancocentral.response.Content;
import com.nosbielc.mixed.salad.bancocentral.response.Response;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

public interface ITransferenciaController {

    @ApiOperation(value = "Lista de Transferencias", nickname = "Transferencia", notes = "Todos as transferencias registradas no banco central.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", responseContainer = "List", response = TransferenciaReponseDto.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    ResponseEntity<Response<Page<TransferenciaReponseDto>>> listar(@RequestParam(value = "pag", defaultValue = "0") Integer pag,
                                                                   @RequestParam(value = "ord", defaultValue = "id") String ord,
                                                                   @RequestParam(value = "dir", defaultValue = "DESC") String dir,
                                                                   @RequestParam(value = "autenticacao", defaultValue = "") String autenticacao,
                                                                   @RequestParam(value = "id", defaultValue = "0") Long id) throws Exception;

    @ApiOperation(value = "Detalhes da Transferencia", nickname = "DetalheTransferencia", notes = "Todos os detalhes da transferencia.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", responseContainer = "Object", response = TransferenciaReponseDto.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    ResponseEntity<Response<Content<TransferenciaReponseDto>>> detalhe(@PathVariable(value = "autenticacao") String autenticacao) throws Exception;

    @ApiOperation(value = "Cria uma transferencia", nickname = "criaTransferencia", notes = "Inicia uma transação entre contas.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", responseContainer = "Object", response = TransferenciaReponseDto.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    ResponseEntity<Response<Content<TransferenciaReponseDto>>> criar(@Valid @RequestBody TransferenciaDto transferenciaDto,
                                                                  BindingResult result) throws Exception;

}

package com.nosbielc.mixed.salad.bancocentral.controllers.utils;

import com.nosbielc.mixed.salad.bancocentral.dtos.BancoDto;
import com.nosbielc.mixed.salad.bancocentral.dtos.NovoBancoDto;
import com.nosbielc.mixed.salad.bancocentral.response.Content;
import com.nosbielc.mixed.salad.bancocentral.response.Response;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface IBancoController {

    @ApiOperation(value = "Lista de bancos", nickname = "Banco", notes = "Todos os bancos cadastrados no banco central.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", responseContainer = "List", response = BancoDto.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    ResponseEntity<Response<Page<BancoDto>>> listar(@RequestParam(value = "pag", defaultValue = "0") Integer pag,
                                                    @RequestParam(value = "ord", defaultValue = "id") String ord,
                                                    @RequestParam(value = "dir", defaultValue = "DESC") String dir) throws Exception;

    @ApiOperation(value = "Detalhe bancos", nickname = "DetalheBanco", notes = "Visualizar datalhes do banco.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", responseContainer = "Object", response = BancoDto.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    ResponseEntity<Response<Content<BancoDto>>> detalhe(@PathVariable(value = "id") Long id) throws Exception;

    @ApiOperation(value = "Inclusão de bancos", nickname = "cirarBanco", notes = "Metodo para incluir um novo banco na base do banco central")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", responseContainer = "Object", response = NovoBancoDto.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    ResponseEntity<Response<Content<BancoDto>>> criar(@Valid @RequestBody NovoBancoDto novoBancoDto,
                                             BindingResult result) throws Exception;
}

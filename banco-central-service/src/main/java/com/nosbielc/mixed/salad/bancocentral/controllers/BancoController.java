package com.nosbielc.mixed.salad.bancocentral.controllers;

import com.nosbielc.mixed.salad.bancocentral.controllers.utils.BancoControllerUtils;
import com.nosbielc.mixed.salad.bancocentral.controllers.utils.IBancoController;
import com.nosbielc.mixed.salad.bancocentral.dtos.BancoDto;
import com.nosbielc.mixed.salad.bancocentral.dtos.NovoBancoDto;
import com.nosbielc.mixed.salad.bancocentral.entities.Banco;
import com.nosbielc.mixed.salad.bancocentral.response.Content;
import com.nosbielc.mixed.salad.bancocentral.response.Response;
import com.nosbielc.mixed.salad.bancocentral.services.impl.BancoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/banco")
@CrossOrigin(origins = "*")
public class BancoController extends BancoControllerUtils implements IBancoController {

    private static final Logger log = LoggerFactory.getLogger(BancoController.class);

    @Autowired
    private BancoServiceImpl bancoService;

    @Value("${pagination.qtd_by_page}")
    private int fetchForPage;

    @Override
    @GetMapping
    public ResponseEntity<Response<Page<BancoDto>>> listar(@RequestParam(value = "pag", defaultValue = "0") Integer pag,
                                                           @RequestParam(value = "ord", defaultValue = "id") String ord,
                                                           @RequestParam(value = "dir", defaultValue = "DESC") String dir) {
        Response<Page<BancoDto>> response = new Response<>();
        PageRequest pageRequest = PageRequest.of(pag, this.fetchForPage, Sort.Direction.valueOf(dir), ord);
        Page<Banco> bancos = this.bancoService.findAllPageable(pageRequest);
        Page<BancoDto> bancosDtos = bancos.map(
                this::toBancoDto
        );

        response.setData(bancosDtos);

        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Response<Content<BancoDto>>> detalhe(@PathVariable(value = "id") Long id) {
        Response<Content<BancoDto>> response = new Response<>();
        Optional<Banco> banco = this.bancoService.findById(id);

        if (banco.isPresent()) {
            response.setData(toContentBancoDto(banco.get()));
        }

        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping
    public ResponseEntity<Response<Content<BancoDto>>> criar(@Valid @RequestBody NovoBancoDto novoBancoDto, BindingResult result) {
        log.info("Registrando o banco: {}", novoBancoDto);
        Response<Content<BancoDto>> response = new Response<>();
        response.setData(
                toContentBancoDto(
                        this.bancoService.persist(
                                toBanco(novoBancoDto))));
        return ResponseEntity.ok(response);
    }

}

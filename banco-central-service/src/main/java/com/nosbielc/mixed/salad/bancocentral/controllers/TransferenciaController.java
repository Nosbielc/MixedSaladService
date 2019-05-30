package com.nosbielc.mixed.salad.bancocentral.controllers;

import com.nosbielc.mixed.salad.bancocentral.controllers.utils.ITransferenciaController;
import com.nosbielc.mixed.salad.bancocentral.controllers.utils.TransferenciaControllerUtils;
import com.nosbielc.mixed.salad.bancocentral.dtos.TransferenciaDto;
import com.nosbielc.mixed.salad.bancocentral.dtos.TransferenciaReponseDto;
import com.nosbielc.mixed.salad.bancocentral.entities.Transferencia;
import com.nosbielc.mixed.salad.bancocentral.response.Content;
import com.nosbielc.mixed.salad.bancocentral.response.Response;
import com.nosbielc.mixed.salad.bancocentral.services.ITrasnferenciaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/transferencia")
@CrossOrigin(origins = "*")
public class TransferenciaController extends TransferenciaControllerUtils implements ITransferenciaController {

    private static final Logger log = LoggerFactory.getLogger(TransferenciaController.class);

    @Autowired
    private ITrasnferenciaService trasnferenciaService;

    @Value("${pagination.qtd_by_page}")
    private int fetchForPage;

    @Override
    @GetMapping
    public ResponseEntity<Response<Page<TransferenciaReponseDto>>> listar(@RequestParam(value = "pag", defaultValue = "0") Integer pag,
                                                                          @RequestParam(value = "ord", defaultValue = "id") String ord,
                                                                          @RequestParam(value = "dir", defaultValue = "DESC") String dir,
                                                                          @RequestParam(value = "autenticacao", defaultValue = "") String autenticacao,
                                                                          @RequestParam(value = "id", defaultValue = "0") Long id) {
        Response<Page<TransferenciaReponseDto>> response = new Response<>();
        PageRequest pageRequest = PageRequest.of(pag, this.fetchForPage, Sort.Direction.valueOf(dir), ord);
        Page<Transferencia> transferencias = Page.empty();

        if (autenticacao != null && !autenticacao.equalsIgnoreCase("")) {
            transferencias = this.trasnferenciaService.findOneAutenticacao(autenticacao, pageRequest);
        } else if (id > 0) {
            Optional<Transferencia> transTrat = this.trasnferenciaService.findById(id);
            if (transTrat.isPresent()) {
                transferencias = (Page<Transferencia>) transTrat.get();
            }
        } else {
            transferencias = this.trasnferenciaService.findAllPageable(pageRequest);
        }

        Page<TransferenciaReponseDto> transferenciaDtos = transferencias.map(TransferenciaControllerUtils::toTransferenciaResponseDto);

        response.setData(transferenciaDtos);

        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/{autenticacao}")
    public ResponseEntity<Response<Content<TransferenciaReponseDto>>> detalhe(@PathVariable(value = "autenticacao") String autenticacao) {
        Response<Content<TransferenciaReponseDto>> response = new Response<>();
        Optional<Transferencia> transferencia = this.trasnferenciaService.findByAutenticacao(autenticacao);

        if (transferencia.isPresent())
            response.setData(toContentTransferenciaResponseDto(transferencia.get()));

        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping
    public ResponseEntity<Response<Content<TransferenciaReponseDto>>> criar(@Valid @RequestBody TransferenciaDto transferenciaDto,
                                                                   BindingResult result) {
        Response<Content<TransferenciaReponseDto>> response = new Response<>();
        log.info("Registrando a nova Transação: {}", transferenciaDto);
        try {
            return ResponseEntity.ok(this.criaTransferencia(transferenciaDto));
        } catch (Exception e) {
            response.addError(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}

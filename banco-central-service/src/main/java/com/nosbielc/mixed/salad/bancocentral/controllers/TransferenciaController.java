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
    public ResponseEntity<Response<Page<TransferenciaReponseDto>>> listar(Integer pag, String ord, String dir,
                                                                          String autenticacao, Long id) throws Exception {
        Response<Page<TransferenciaReponseDto>> response = new Response<>();
        PageRequest pageRequest = PageRequest.of(pag, this.fetchForPage, Sort.Direction.valueOf(dir), ord);
        Page<Transferencia> transferencias= null;

        if (!autenticacao.equalsIgnoreCase("")) {
            transferencias = this.trasnferenciaService.findOneAutenticacao(autenticacao, pageRequest);
        } else if (id > 0) {
            transferencias = (Page<Transferencia>) (this.trasnferenciaService.findById(id)).get();
        } else {
            transferencias = this.trasnferenciaService.findAllPageable(pageRequest);
        }

        Page<TransferenciaReponseDto> transferenciaDtos = transferencias.map( trans -> toTransferenciaResponseDto(trans));

        response.setData(transferenciaDtos);

        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/{autenticacao}")
    public ResponseEntity<Response<Content<TransferenciaReponseDto>>> detalhe(String autenticacao) throws Exception {
        Response<Content<TransferenciaReponseDto>> response = new Response<>();
        Optional<Transferencia> transferencia = this.trasnferenciaService.findByAutenticacao(autenticacao);

        if (transferencia.isPresent())
            response.setData(toContentTransferenciaResponseDto(transferencia.get()));

        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping
    public ResponseEntity<Response<Content<TransferenciaReponseDto>>> criar(@Valid @RequestBody TransferenciaDto transferenciaDto,
                                                                   BindingResult result) throws Exception {
        log.info("Registrando a nova Transação: {}", transferenciaDto.toString());
        return ResponseEntity.ok(criaTransferencia(transferenciaDto));
    }
}

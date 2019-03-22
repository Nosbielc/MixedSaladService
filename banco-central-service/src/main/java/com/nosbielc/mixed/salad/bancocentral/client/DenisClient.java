package com.nosbielc.mixed.salad.bancocentral.client;

import com.nosbielc.mixed.salad.bancocentral.client.dto.DenisDeposito;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient("http://10.53.53.63:8080")
public interface DenisClient {

    @PutMapping("/transacaoInterna/depositar/{cpf}")
    Object depositar(@PathVariable(value = "cpf") String cpf, @RequestBody DenisDeposito denisDeposito);

}

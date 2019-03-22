package com.nosbielc.mixed.salad.bancocentral.transferencia.scheduler;

import com.nosbielc.mixed.salad.bancocentral.client.DenisClient;
import com.nosbielc.mixed.salad.bancocentral.entities.Transferencia;
import com.nosbielc.mixed.salad.bancocentral.enums.TransferenciaStatus;
import com.nosbielc.mixed.salad.bancocentral.services.impl.TransferenciaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class SchedulerTransferencia {

    @Autowired
    private TransferenciaServiceImpl transferenciaService;

    @Autowired
    private DenisClient denisClient;

    @Scheduled(fixedRate = 60000)
    public void runChargeLocal() throws Exception {
        System.out.println("Começando a processar as transferencias :" + new Date());

        List<Transferencia> transferenciasPendentes =
                this.transferenciaService.findAllByTransferenciaStatusOrderByIdAsc(TransferenciaStatus.REQUESTED);

            transferenciasPendentes.stream().forEach(transferencia -> {
                try {

//                        Object retorno = this.denisClient.depositar(transferencia.getContaDestino(),
//                                                new DenisDeposito(
//                                                        transferencia.getContaDestino(),
//                                                        transferencia.getValorTransferencia().doubleValue()));

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());

//                       transferencia.setTransferenciaStatus(TransferenciaStatus.ERROR_PROCESSING);
//                       this.transferenciaService.persist(transferencia);
                    }
            });


    }

}

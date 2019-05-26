package com.nosbielc.mixed.salad.bancocentral.transferencia.scheduler;

import com.nosbielc.mixed.salad.bancocentral.client.DenisClient;
import com.nosbielc.mixed.salad.bancocentral.client.dto.DenisDeposito;
import com.nosbielc.mixed.salad.bancocentral.entities.Transferencia;
import com.nosbielc.mixed.salad.bancocentral.enums.TransferenciaStatus;
import com.nosbielc.mixed.salad.bancocentral.services.impl.TransferenciaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class SchedulerTransferencia {

    private static final Logger log = LoggerFactory.getLogger(SchedulerTransferencia.class);

    @Autowired
    private TransferenciaServiceImpl transferenciaService;

    @Autowired
    private DenisClient denisClient;

    @Value("${banco.central.schedulers.enabled}")
    private boolean enabled;

    @Scheduled(fixedRate = 60000)
    public void runChargeLocal() {

        if (enabled) {

            log.info("Começando a processar as transferencias : {} ", new Date());

            List<Transferencia> transferenciasPendentes =
                    this.transferenciaService.findAllByTransferenciaStatusOrderByIdAsc(TransferenciaStatus.REQUESTED);

            transferenciasPendentes.stream().forEach(transferencia -> {
                try {

                    this.denisClient.depositar(transferencia.getContaDestino(),
                            new DenisDeposito(
                                    transferencia.getContaDestino(),
                                    transferencia.getValorTransferencia().doubleValue()));

                } catch (Exception ex) {

                    log.error(ex.getMessage());
                    transferencia.setTransferenciaStatus(TransferenciaStatus.ERROR_PROCESSING);
                    this.transferenciaService.persist(transferencia);

                }
            });

        } else {
            log.info("Tarefa desabilitada por configuração. ");
        }
    }

}

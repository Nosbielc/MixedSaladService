package com.nosbielc.mixed.salad.bancocentral.transferencia;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

public class JobCompletionListener extends JobExecutionListenerSupport {

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            System.out.println("BATCH JOB SAFRASYSTEM COLLECTOR COMPLETED SUCCESSFULLY");
        } else if (jobExecution.getStatus() == BatchStatus.STARTED) {
            System.out.println("BATCH JOB SAFRASYSTEM COLLECTOR STARTED");
        }
    }

}

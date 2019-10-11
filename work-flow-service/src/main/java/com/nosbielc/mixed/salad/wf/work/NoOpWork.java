package com.nosbielc.mixed.salad.wf.work;

import java.util.UUID;

/**
 * No operation work.
 *
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 11/10/2019
 * @project mixed-salad-service
 */
public class NoOpWork implements Work {

    @Override
    public String getName() {
        return UUID.randomUUID().toString();
    }

    @Override
    public WorkReport call() {
        return new DefaultWorkReport(WorkStatus.COMPLETED);
    }
}

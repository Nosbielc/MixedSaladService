package com.nosbielc.mixed.salad.wf.workflow;

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 11/10/2019
 * @project mixed-salad-service
 */
abstract class AbstractWorkFlow implements WorkFlow {

    private String name;

    AbstractWorkFlow(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

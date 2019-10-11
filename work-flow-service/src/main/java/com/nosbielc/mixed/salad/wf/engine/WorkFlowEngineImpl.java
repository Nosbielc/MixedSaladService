package com.nosbielc.mixed.salad.wf.engine;

import com.nosbielc.mixed.salad.wf.work.WorkReport;
import com.nosbielc.mixed.salad.wf.workflow.WorkFlow;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 11/10/2019
 * @project mixed-salad-service
 */
class WorkFlowEngineImpl implements WorkFlowEngine {

    private static final Logger LOGGER = Logger.getLogger(WorkFlowEngineImpl.class.getName());

    public WorkReport run(WorkFlow workFlow) {
        LOGGER.log(Level.INFO, "Running workflow ''{0}''", workFlow.getName());
        return workFlow.call();
    }

}

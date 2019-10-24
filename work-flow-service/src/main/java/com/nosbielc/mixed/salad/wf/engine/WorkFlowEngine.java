package com.nosbielc.mixed.salad.wf.engine;

import com.nosbielc.mixed.salad.wf.work.WorkReport;
import com.nosbielc.mixed.salad.wf.workflow.WorkFlow;

/**
 * Interface for workflow engine.
 *
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 11/10/2019
 * @project mixed-salad-service
 */
public interface WorkFlowEngine {

    /**
     * Run the given workflow and return its report.
     *
     * @param workFlow to run
     * @return workflow report
     */
    WorkReport run(WorkFlow workFlow);

}

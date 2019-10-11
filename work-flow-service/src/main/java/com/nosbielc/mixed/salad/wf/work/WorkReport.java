package com.nosbielc.mixed.salad.wf.work;

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 11/10/2019
 * @project mixed-salad-service
 */
public interface WorkReport {

    /**
     * Get work execution status.
     * @return execution status
     */
    WorkStatus getStatus();

    /**
     * Get error if any.
     * @return error
     */
    Throwable getError();

}

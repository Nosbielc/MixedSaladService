package com.nosbielc.mixed.salad.wf.work;

import java.util.StringJoiner;

/**
 * Default implementation of {@link WorkReport}.
 *
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 11/10/2019
 * @project mixed-salad-service
 */
public class DefaultWorkReport implements WorkReport {

    private WorkStatus status;
    private Throwable error;

    /**
     * Create a new {@link DefaultWorkReport}.
     *
     * @param status of work
     */
    public DefaultWorkReport(WorkStatus status) {
        this.status = status;
    }

    /**
     * Create a new {@link DefaultWorkReport}.
     *
     * @param status of work
     * @param error if any
     */
    public DefaultWorkReport(WorkStatus status, Throwable error) {
        this(status);
        this.error = error;
    }

    public WorkStatus getStatus() {
        return status;
    }

    public Throwable getError() {
        return error;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DefaultWorkReport.class.getSimpleName() + "[", "]")
                .add("status=" + status)
                .add("error=" + (error == null ? "''" : error))
                .toString();
    }

}

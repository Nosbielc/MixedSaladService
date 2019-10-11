package com.nosbielc.mixed.salad.wf.workflow;

import com.nosbielc.mixed.salad.wf.work.WorkReport;
import com.nosbielc.mixed.salad.wf.work.WorkStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Aggregate report of all works reports.
 *
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 11/10/2019
 * @project mixed-salad-service
 */
public class ParallelFlowReport implements WorkReport {

    private List<WorkReport> reports;

    /**
     * Create a new {@link ParallelFlowReport}.
     */
    public ParallelFlowReport() {
        this(new ArrayList<>());
    }

    /**
     * Create a new {@link ParallelFlowReport}.
     * @param reports of works executed in parallel
     */
    public ParallelFlowReport(List<WorkReport> reports) {
        this.reports = reports;
    }

    /**
     * Get partial reports.
     *
     * @return partial reports
     */
    public List<WorkReport> getReports() {
        return reports;
    }

    void add(WorkReport workReport) {
        reports.add(workReport);
    }

    void addAll(List<WorkReport> workReports) {
        reports.addAll(workReports);
    }

    /**
     * Return the status of the parallel flow.
     *
     * The status of a parallel flow is defined as follows:
     * <ul>
     *     <li>{@link com.nosbielc.mixed.salad.wf.work.WorkStatus#STARTED}: If one of the works has started</li>
     *     <li>{@link com.nosbielc.mixed.salad.wf.work.WorkStatus#RUNNING}: If one of the works has running</li>
     *     <li>{@link com.nosbielc.mixed.salad.wf.work.WorkStatus#WAITING}: If one of the works has waiting</li>
     *     <li>{@link com.nosbielc.mixed.salad.wf.work.WorkStatus#COMPLETED}: If all works have successfully completed</li>
     *     <li>{@link com.nosbielc.mixed.salad.wf.work.WorkStatus#FAILED}: If one of the works has failed</li>
     * </ul>
     * @return workflow status
     */
    public WorkStatus getStatus() {
        for (WorkReport report : reports) {
            if (report.getStatus().equals(WorkStatus.FAILED)) {
                return WorkStatus.FAILED;
            }
        }
        return WorkStatus.COMPLETED;
    }

    /**
     * Return the first error of partial reports.
     *
     * @return the first error of partial reports.
     */
    @Override
    public Throwable getError() {
        for (WorkReport report : reports) {
            Throwable error = report.getError();
            if (error != null) {
                return error;
            }
        }
        return null;
    }
}

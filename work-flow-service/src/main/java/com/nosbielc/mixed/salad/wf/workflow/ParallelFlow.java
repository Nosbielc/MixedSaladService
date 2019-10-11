package com.nosbielc.mixed.salad.wf.workflow;

import com.nosbielc.mixed.salad.wf.work.Work;
import com.nosbielc.mixed.salad.wf.work.WorkReport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * A parallel flow executes a set of works in parallel.
 *
 * The status of a parallel flow execution is defined as:
 *
 * <ul>
 *     <li>{@link com.nosbielc.mixed.salad.wf.work.WorkStatus#STARTED}: If one of the works has started</li>
 *     <li>{@link com.nosbielc.mixed.salad.wf.work.WorkStatus#RUNNING}: If one of the works has running</li>
 *     <li>{@link com.nosbielc.mixed.salad.wf.work.WorkStatus#WAITING}: If one of the works has waiting</li>
 *     <li>{@link com.nosbielc.mixed.salad.wf.work.WorkStatus#COMPLETED}: If all works have successfully completed</li>
 *     <li>{@link com.nosbielc.mixed.salad.wf.work.WorkStatus#FAILED}: If one of the works has failed</li>
 * </ul>
 *
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 11/10/2019
 * @project mixed-salad-service
 */
public class ParallelFlow extends AbstractWorkFlow {

    private List<Work> works = new ArrayList<>();
    private ParallelFlowExecutor workExecutor;

    ParallelFlow(String name, List<Work> works, ParallelFlowExecutor parallelFlowExecutor) {
        super(name);
        this.works.addAll(works);
        this.workExecutor = parallelFlowExecutor;
    }

    /**
     * {@inheritDoc}
     */
    public ParallelFlowReport call() {
        ParallelFlowReport workFlowReport = new ParallelFlowReport();
        List<WorkReport> workReports = workExecutor.executeInParallel(works);
        workFlowReport.addAll(workReports);
        return workFlowReport;
    }

    public static class Builder {

        private String name;
        private List<Work> works;

        private Builder() {
            this.name = UUID.randomUUID().toString();
            this.works = new ArrayList<>();
        }

        public static Builder aNewParallelFlow() {
            return new Builder();
        }

        public Builder named(String name) {
            this.name = name;
            return this;
        }

        public Builder execute(Work... works) {
            this.works.addAll(Arrays.asList(works));
            return this;
        }

        public ParallelFlow build() {
            return new ParallelFlow(name, works, new ParallelFlowExecutor());
        }
    }
}

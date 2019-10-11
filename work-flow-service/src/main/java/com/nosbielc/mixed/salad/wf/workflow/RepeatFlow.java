package com.nosbielc.mixed.salad.wf.workflow;

import com.nosbielc.mixed.salad.wf.work.NoOpWork;
import com.nosbielc.mixed.salad.wf.work.Work;
import com.nosbielc.mixed.salad.wf.work.WorkReport;
import com.nosbielc.mixed.salad.wf.work.WorkReportPredicate;

import java.util.UUID;

/**
 * A repeat flow executes a work repeatedly until its report is satisfied by a given predicate.
 *
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 11/10/2019
 * @project mixed-salad-service
 */
public class RepeatFlow extends AbstractWorkFlow {

    private Work work;
    private WorkReportPredicate predicate;

    RepeatFlow(String name, Work work, WorkReportPredicate predicate) {
        super(name);
        this.work = work;
        this.predicate = predicate;
    }

    /**
     * {@inheritDoc}
     */
    public WorkReport call() {
        WorkReport workReport;
        do {
            workReport = work.call();
        } while (predicate.apply(workReport));
        return workReport;
    }

    public static class Builder {

        private String name;
        private Work work;
        private WorkReportPredicate predicate;

        private Builder() {
            this.name = UUID.randomUUID().toString();
            this.work = new NoOpWork();
            this.predicate = WorkReportPredicate.ALWAYS_FALSE;
        }

        public static Builder aNewRepeatFlow() {
            return new Builder();
        }

        public Builder named(String name) {
            this.name = name;
            return this;
        }

        public Builder repeat(Work work) {
            this.work = work;
            return this;
        }

        public Builder times(int times) {
            return until(WorkReportPredicate.TimesPredicate.times(times));
        }

        public Builder until(WorkReportPredicate predicate) {
            this.predicate = predicate;
            return this;
        }

        public RepeatFlow build() {
            return new RepeatFlow(name, work, predicate);
        }
    }
}

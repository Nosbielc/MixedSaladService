package com.nosbielc.mixed.salad.wf.workflow;

import com.nosbielc.mixed.salad.wf.work.NoOpWork;
import com.nosbielc.mixed.salad.wf.work.Work;
import com.nosbielc.mixed.salad.wf.work.WorkReport;
import com.nosbielc.mixed.salad.wf.work.WorkReportPredicate;

import java.util.UUID;

/**
 * A conditional flow is defined by 4 artifacts:
 *
 * <ul>
 *     <li>The work to execute first</li>
 *     <li>A predicate for the conditional logic</li>
 *     <li>The work to execute if the predicate is satisfied</li>
 *     <li>The work to execute if the predicate is not satisfied (optional)</li>
 * </ul>
 *
 * @see Builder
 *
 * @author Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 11/10/2019
 * @project mixed-salad-service
 */
public class ConditionalFlow extends AbstractWorkFlow {

    private Work toExecute, nextOnPredicateSuccess, nextOnPredicateFailure;
    private WorkReportPredicate predicate;

    ConditionalFlow(String name, Work toExecute, Work nextOnPredicateSuccess, Work nextOnPredicateFailure, WorkReportPredicate predicate) {
        super(name);
        this.toExecute = toExecute;
        this.nextOnPredicateSuccess = nextOnPredicateSuccess;
        this.nextOnPredicateFailure = nextOnPredicateFailure;
        this.predicate = predicate;
    }

    /**
     * {@inheritDoc}
     */
    public WorkReport call() {
        WorkReport jobReport = toExecute.call();
        if (predicate.apply(jobReport)) {
            jobReport = nextOnPredicateSuccess.call();
        } else {
            if (nextOnPredicateFailure != null && !(nextOnPredicateFailure instanceof NoOpWork)) { // else is optional
                jobReport = nextOnPredicateFailure.call();
            }
        }
        return jobReport;
    }

    public static class Builder {

        private String name;
        private Work toExecute, nextOnPredicateSuccess, nextOnPredicateFailure;
        private WorkReportPredicate predicate;

        private Builder() {
            this.name = UUID.randomUUID().toString();
            this.toExecute = new NoOpWork();
            this.nextOnPredicateSuccess = new NoOpWork();
            this.nextOnPredicateFailure = new NoOpWork();
            this.predicate = WorkReportPredicate.ALWAYS_FALSE;
        }

        public static Builder aNewConditionalFlow() {
            return new Builder();
        }

        public Builder named(String name) {
            this.name = name;
            return this;
        }

        public Builder execute(Work work) {
            this.toExecute = work;
            return this;
        }

        public Builder when(WorkReportPredicate predicate) {
            this.predicate = predicate;
            return this;
        }

        public Builder then(Work work) {
            this.nextOnPredicateSuccess = work;
            return this;
        }

        public Builder otherwise(Work work) {
            this.nextOnPredicateFailure = work;
            return this;
        }

        public ConditionalFlow build() {
            return new ConditionalFlow(name, toExecute, nextOnPredicateSuccess, nextOnPredicateFailure, predicate);
        }
    }
}

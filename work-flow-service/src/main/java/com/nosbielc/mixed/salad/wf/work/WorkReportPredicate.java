package com.nosbielc.mixed.salad.wf.work;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A predicate interface on work report.
 *
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 11/10/2019
 * @project mixed-salad-service
 */
@FunctionalInterface
public interface WorkReportPredicate {

    /**
     * Apply the predicate on the given work report.
     * @param workReport on which the predicate should be applied
     * @return true if the predicate applies on the given report, false otherwise
     */
    boolean apply(WorkReport workReport);

    WorkReportPredicate ALWAYS_TRUE = workReport -> true;
    WorkReportPredicate ALWAYS_FALSE = workReport -> false;
    WorkReportPredicate COMPLETED = workReport -> workReport.getStatus().equals(WorkStatus.COMPLETED);
    WorkReportPredicate FAILED = workReport -> workReport.getStatus().equals(WorkStatus.FAILED);
    WorkReportPredicate STARTED = workReport -> workReport.getStatus().equals(WorkStatus.STARTED);
    WorkReportPredicate RUNNING = workReport -> workReport.getStatus().equals(WorkStatus.RUNNING);
    WorkReportPredicate WAITING = workReport -> workReport.getStatus().equals(WorkStatus.WAITING);

    /**
     * A predicate that returns true after a given number of times.
     *
     * @author Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
     */
    class TimesPredicate implements WorkReportPredicate {

        private int times;

        private AtomicInteger counter = new AtomicInteger();

        public TimesPredicate(int times) {
            this.times = times;
        }

        @Override
        public boolean apply(WorkReport workReport) {
            return counter.incrementAndGet() != times;
        }

        public static TimesPredicate times(int times) {
            return new TimesPredicate(times);
        }
    }

}

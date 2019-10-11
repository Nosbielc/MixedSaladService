package com.nosbielc.mixed.salad.wf.workflow;

import com.nosbielc.mixed.salad.wf.work.Work;
import com.nosbielc.mixed.salad.wf.work.WorkReport;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.nosbielc.mixed.salad.wf.work.WorkStatus.FAILED;

/**
 * A sequential flow executes a set of works in sequence.
 *
 * If a work fails, next works in the pipeline will be skipped.
 *
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 11/10/2019
 * @project mixed-salad-service
 */
public class SequentialFlow extends AbstractWorkFlow {

    private static final Logger LOGGER = Logger.getLogger(SequentialFlow.class.getName());

    private List<Work> works = new ArrayList<>();

    SequentialFlow(String name, List<Work> works) {
        super(name);
        this.works.addAll(works);
    }

    /**
     * {@inheritDoc}
     */
    public WorkReport call() {
        WorkReport workReport = null;
        for (Work work : works) {
            workReport = work.call();
            if (workReport != null && FAILED.equals(workReport.getStatus())) {
                LOGGER.log(Level.INFO, "Work ''{0}'' has failed, skipping subsequent works", work.getName());
                break;
            }
        }
        return workReport;
    }

    public static class Builder {

        private String name;
        private List<Work> works;

        private Builder() {
            this.name = UUID.randomUUID().toString();
            this.works = new ArrayList<>();
        }

        public static Builder aNewSequentialFlow() {
            return new Builder();
        }

        public Builder named(String name) {
            this.name = name;
            return this;
        }

        public Builder execute(Work work) {
            this.works.add(work);
            return this;
        }

        public Builder then(Work work) {
            this.works.add(work);
            return this;
        }

        public SequentialFlow build() {
            return new SequentialFlow(name, works);
        }
    }
}

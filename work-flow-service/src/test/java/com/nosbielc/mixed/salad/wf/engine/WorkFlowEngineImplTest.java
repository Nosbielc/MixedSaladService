package com.nosbielc.mixed.salad.wf.engine;

import com.nosbielc.mixed.salad.wf.work.DefaultWorkReport;
import com.nosbielc.mixed.salad.wf.work.Work;
import com.nosbielc.mixed.salad.wf.work.WorkReport;
import com.nosbielc.mixed.salad.wf.work.WorkStatus;
import com.nosbielc.mixed.salad.wf.workflow.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static com.nosbielc.mixed.salad.wf.engine.WorkFlowEngineBuilder.aNewWorkFlowEngine;
import static com.nosbielc.mixed.salad.wf.work.WorkReportPredicate.COMPLETED;
import static com.nosbielc.mixed.salad.wf.workflow.ConditionalFlow.Builder.aNewConditionalFlow;
import static com.nosbielc.mixed.salad.wf.workflow.ParallelFlow.Builder.aNewParallelFlow;
import static com.nosbielc.mixed.salad.wf.workflow.RepeatFlow.Builder.aNewRepeatFlow;
import static com.nosbielc.mixed.salad.wf.workflow.SequentialFlow.Builder.aNewSequentialFlow;

@RunWith(SpringRunner.class)
public class WorkFlowEngineImplTest {

    private WorkFlowEngine workFlowEngine = new WorkFlowEngineImpl();

    @Test
    public void run() throws Exception {
        // given
        WorkFlow workFlow = Mockito.mock(WorkFlow.class);

        // when
        workFlowEngine.run(workFlow);

        // then
        Mockito.verify(workFlow).call();
    }

    /**
     * The following tests are not really unit tests, but serve as examples of how to create a workflow and execute it
     */

    @Test
    public void composeWorkFlowFromSeparateFlowsAndExecuteIt() throws Exception {

        PrintMessageWork work1 = new PrintMessageWork("foo");
        PrintMessageWork work2 = new PrintMessageWork("hello");
        PrintMessageWork work3 = new PrintMessageWork("world");
        PrintMessageWork work4 = new PrintMessageWork("done");

        RepeatFlow repeatFlow = aNewRepeatFlow()
                .named("print foo 3 times")
                .repeat(work1)
                .times(3)
                .build();

        ParallelFlow parallelFlow = aNewParallelFlow()
                .named("print 'hello' and 'world' in parallel")
                .execute(work2, work3)
                .build();

        ConditionalFlow conditionalFlow = aNewConditionalFlow()
                .execute(parallelFlow)
                .when(COMPLETED)
                .then(work4)
                .build();

        SequentialFlow sequentialFlow = aNewSequentialFlow()
                .execute(repeatFlow)
                .then(conditionalFlow)
                .build();

        WorkFlowEngine workFlowEngine = aNewWorkFlowEngine().build();
        WorkReport workReport = workFlowEngine.run(sequentialFlow);
        System.out.println("workflow report = " + workReport);
    }

    @Test
    public void defineWorkFlowInlineAndExecuteIt() throws Exception {

        PrintMessageWork work1 = new PrintMessageWork("foo");
        PrintMessageWork work2 = new PrintMessageWork("hello");
        PrintMessageWork work3 = new PrintMessageWork("world");
        PrintMessageWork work4 = new PrintMessageWork("done");

        WorkFlow workflow = aNewSequentialFlow()
                .execute(aNewRepeatFlow()
                            .named("print foo 3 times")
                            .repeat(work1)
                            .times(3)
                            .build())
                .then(aNewConditionalFlow()
                        .execute(aNewParallelFlow()
                                    .named("print 'hello' and 'world' in parallel")
                                    .execute(work2, work3)
                                    .build())
                        .when(COMPLETED)
                        .then(work4)
                        .build())
                .build();

        WorkFlowEngine workFlowEngine = aNewWorkFlowEngine().build();
        WorkReport workReport = workFlowEngine.run(workflow);
        System.out.println("workflow report = " + workReport);
    }

    static class PrintMessageWork implements Work {

        private String message;

        public PrintMessageWork(String message) {
            this.message = message;
        }

        public String getName() {
            return "print message work";
        }

        public WorkReport call() {
            System.out.println(message);
            return new DefaultWorkReport(WorkStatus.COMPLETED);
        }

    }
}
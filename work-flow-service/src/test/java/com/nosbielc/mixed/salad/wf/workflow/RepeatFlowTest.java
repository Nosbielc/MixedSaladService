package com.nosbielc.mixed.salad.wf.workflow;

import com.nosbielc.mixed.salad.wf.work.Work;
import com.nosbielc.mixed.salad.wf.work.WorkReportPredicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RepeatFlowTest {

    @Test
    public void testRepeatUntil() throws Exception {
        // given
        Work work = Mockito.mock(Work.class);
        WorkReportPredicate predicate = WorkReportPredicate.ALWAYS_FALSE;
        RepeatFlow repeatFlow = RepeatFlow.Builder.aNewRepeatFlow()
                .repeat(work)
                .until(predicate)
                .build();

        // when
        repeatFlow.call();

        // then
        Mockito.verify(work, Mockito.times(1)).call();
    }

    @Test
    public void testRepeatTimes() throws Exception {
        // given
        Work work = Mockito.mock(Work.class);
        RepeatFlow repeatFlow = RepeatFlow.Builder.aNewRepeatFlow()
                .repeat(work)
                .times(3)
                .build();

        // when
        repeatFlow.call();

        // then
        Mockito.verify(work, Mockito.times(3)).call();
    }

}
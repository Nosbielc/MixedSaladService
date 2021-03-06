package com.nosbielc.mixed.salad.wf.workflow;

import com.nosbielc.mixed.salad.wf.work.Work;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class SequentialFlowTest {

    @Test
    public void call() throws Exception {
        // given
        Work work1 = Mockito.mock(Work.class);
        Work work2 = Mockito.mock(Work.class);
        Work work3 = Mockito.mock(Work.class);
        SequentialFlow sequentialFlow = SequentialFlow.Builder.aNewSequentialFlow()
                .execute(work1)
                .then(work2)
                .then(work3)
                .build();

        // when
        sequentialFlow.call();

        // then
        InOrder inOrder = Mockito.inOrder(work1, work2, work3);
        inOrder.verify(work1, Mockito.times(1)).call();
        inOrder.verify(work2, Mockito.times(1)).call();
        inOrder.verify(work3, Mockito.times(1)).call();
    }

}
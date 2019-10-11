package com.nosbielc.mixed.salad.wf.workflow;

import com.nosbielc.mixed.salad.wf.work.Work;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class ParallelFlowTest {

    @Test
    public void call() throws Exception {
        // given
        Work work1 = Mockito.mock(Work.class);
        Work work2 = Mockito.mock(Work.class);
        ParallelFlowExecutor parallelFlowExecutor = Mockito.mock(ParallelFlowExecutor.class);
        List<Work> works = Arrays.asList(work1, work2);
        ParallelFlow parallelFlow = new ParallelFlow("pf", works, parallelFlowExecutor);

        // when
        ParallelFlowReport parallelFlowReport = parallelFlow.call();

        // then
        Assertions.assertThat(parallelFlowReport).isNotNull();
        Mockito.verify(parallelFlowExecutor).executeInParallel(works);
    }

}
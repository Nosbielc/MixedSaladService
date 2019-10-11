package com.nosbielc.mixed.salad.wf.workflow;

import com.nosbielc.mixed.salad.wf.work.Work;
import com.nosbielc.mixed.salad.wf.work.WorkReportPredicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ConditionalFlowTest {

    @Test
    public void callOnPredicateSuccess() {
        // given
        Work toExecute = Mockito.mock(Work.class);
        Work nextOnPredicateSuccess = Mockito.mock(Work.class);
        Work nextOnPredicateFailure = Mockito.mock(Work.class);
        WorkReportPredicate predicate = WorkReportPredicate.ALWAYS_TRUE;
        ConditionalFlow conditionalFlow = ConditionalFlow.Builder.aNewConditionalFlow()
                .execute(toExecute)
                .when(predicate)
                .then(nextOnPredicateSuccess)
                .otherwise(nextOnPredicateFailure)
                .build();

        // when
        conditionalFlow.call();

        // then
        Mockito.verify(toExecute, Mockito.times(1)).call();
        Mockito.verify(nextOnPredicateSuccess, Mockito.times(1)).call();
        Mockito.verify(nextOnPredicateFailure, Mockito.never()).call();
    }

    @Test
    public void callOnPredicateFailure() {
        // given
        Work toExecute = Mockito.mock(Work.class);
        Work nextOnPredicateSuccess = Mockito.mock(Work.class);
        Work nextOnPredicateFailure = Mockito.mock(Work.class);
        WorkReportPredicate predicate = WorkReportPredicate.ALWAYS_FALSE;
        ConditionalFlow conditionalFlow = ConditionalFlow.Builder.aNewConditionalFlow()
                .execute(toExecute)
                .when(predicate)
                .then(nextOnPredicateSuccess)
                .otherwise(nextOnPredicateFailure)
                .build();

        // when
        conditionalFlow.call();

        // then
        Mockito.verify(toExecute, Mockito.times(1)).call();
        Mockito.verify(nextOnPredicateFailure, Mockito.times(1)).call();
        Mockito.verify(nextOnPredicateSuccess, Mockito.never()).call();
    }

}
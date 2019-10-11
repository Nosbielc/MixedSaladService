package com.nosbielc.mixed.salad.wf.work;

import java.util.concurrent.Callable;

/**
 * Implementations of this interface must:
 * <ul>
 *     <li>catch exceptions and return {@link WorkStatus#FAILED}</li>
 *     <li>make sure the work in finished in a finite amount of time</li>
 * </ul>
 *
 * Work name must be unique within a workflow.
 *
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 11/10/2019
 * @project mixed-salad-service
 */
public interface Work extends Callable<WorkReport> {

    String getName();

    WorkReport call();
}

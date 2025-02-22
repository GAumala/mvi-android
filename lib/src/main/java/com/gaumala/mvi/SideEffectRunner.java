package com.gaumala.mvi;

/**
 * <p>Interface for objects that can take a side-effect value, "run" it,
 * wait for its completion and finally submit an action to report the
 * result.</p>
 *
 * <p>Implementations should take care of running "slow" tasks on background
 * threads and call {@link ActionSink#submitAction(Action)} on the main thread
 * once the work is done.</p>
 * @param <T> The type of the state.
 * @param <U> The type of the side-effect.
 */
public interface SideEffectRunner<T, U> {

    /**
     * Takes a side-effect value, executes it, and submits a new action with
     * the result once it completes. Implementations should take care of
     * running "slow" tasks on background threads.
     * @param sink A sink that can submit actions to trigger state changes. You
     *             should only invoke this in the main thread.
     * @param sideEffect A side-effect value. An immutable object with all
     *                   the data needed to run the desired task.
     */
    public void runSideEffect(ActionSink<T, U> sink, U sideEffect);
}
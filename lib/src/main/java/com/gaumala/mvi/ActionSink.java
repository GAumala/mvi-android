package com.gaumala.mvi;

/**
 * An interface for an object that can receive {@link Action} values, trigger
 * a state change and run any side-effect returned by the
 * {@link Action#update(Object)} method.
 *
 * Implementations are not meant to be thread safe. Always call these methods
 * from the UI thread.
 * @param <T> The type of the State
 * @param <U> The type of the Side-Effect
 */
public interface ActionSink<T, U> {

    /**
     * <p>Submits an action to trigger a state change.</p>
     *
     * <p>The implementation should read the current state and use it to execute
     * the {@link Action#update(Object)}. The resulting {@link Update}
     * value should then be used to do the following two things:</p>
     *
     * <ol>
     *     <li>notify any subscribed UIs to rebind with the new state.</li>
     *     <li>Run the returned side effect (if any).</li>
     * </ol>
     *
     * @param action The action value to submit.
     */
    void submitAction(Action<T, U> action);
}

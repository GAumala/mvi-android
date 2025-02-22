package com.gaumala.mvi;

import androidx.annotation.NonNull;

/**
 * <p>A value that triggers a state change. It implements an update method that
 * takes the current state and returns a new one, with an optional side effect.
 * Actions are state transitions. They model your
 * business logic.</p>
 *
 * <p>Actions are assumed to be plain immutable data.</p>
 * @param <T> The type of the state. It is assumed to be plain immutable data.
 * @param <U> The type of the side effect. It is assumed to be plain immutable
 *           data.
 */
public abstract class Action<T, U> {

    /**
     * <p>A function that receives the current state and returns a new one. State
     * values are assumed to be immutable. You MUST return a new state value
     * instead of mutating the current one. This should be enforced at the type
     * level.</p>
     *
     * <p>This function is assumed to be pure. It should have no side effects and
     * and same arguments should returns the same results. To calculate the new
     * state it can't use anything but the current state. It can't perform I/O,
     * or inspect some global variable.</p>
     *
     * <p>If you want to trigger some I/O operation like an HTTP request or
     * database access you can return a side-effect value that will be passed
     * to a {@link SideEffectRunner} so that it is executed afterwards without
     * violating the purity of this function.</p>
     *
     * @param state The current state value.
     * @return An update value with the new state and optionally a side-effect
     * value.
     */
    public abstract @NonNull Update<T, U> update(T state);
}
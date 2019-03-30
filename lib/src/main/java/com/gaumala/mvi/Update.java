package com.gaumala.mvi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * <p>The type of the result of the {@link Action#update(Object)} method.</p>
 *
 * <p>It has two fields: state and side effect. The former is the new state
 * after playing the action while the latter is an optional value to pass to a
 * {@link SideEffectRunner} so that it can be executed.</p>
 *
 * <p>The state value should never be null. The side-effect can be null because
 * it is optional.</p>
 * @param <T> The type of the state.
 * @param <U> The type of the side effect.
 */
public class Update<T, U>{

    @NonNull final public T state;
    @Nullable final public U sideEffect;

    public Update(@NonNull T state, @Nullable U sideEffect) {
        this.state = state;
        this.sideEffect = sideEffect;
    }

    public Update(@NonNull T state) {
        this.state = state;
        this.sideEffect = null;
    }
}

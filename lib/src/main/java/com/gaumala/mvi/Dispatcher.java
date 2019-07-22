package com.gaumala.mvi;

import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

/**
 * An object holds the application state and dispatches actions to update the
 * state.
 * @param <T>
 * @param <U>
 */
public class Dispatcher<T, U> implements ActionSink<T, U> {
    private final MutableLiveData<T> mutableLiveState = new MutableLiveData<>();
    private final SideEffectRunner<T, U> sideEffectRunner;
    private OnUpdateListener<T, U> listener;
    private final Handler handler = new Handler();

    /**
     * Dispatcher Constructor
     * @param runner An object that takes a Side-Effect value and "runs" it
     *               (e.g. executes an HTTP request or writes a file), waits for
     *               the result and submits a new action to update the state..
     * @param initialValue The initial state of the application.
     */
    public Dispatcher(SideEffectRunner<T, U> runner, @NonNull T initialValue) {
        mutableLiveState.setValue(initialValue);
        this.sideEffectRunner = runner;
    }

    /**
     *
     * @return A live data object containing the current application state.
     */
    @NonNull public LiveData<T> getLiveState() {
        return mutableLiveState;
    }


    @Override
    public void submitAction(Action<T, U> action) {
        dispatch(action);
    }

    private void dispatch(Action<T, U> action) {
        final Update<T, U> update = action.update(mutableLiveState.getValue());

        mutableLiveState.setValue(update.state);
        if (listener != null)
            listener.onUpdate(action, update.state, update.sideEffect);

        // Side effects should run last, on the next tick to avoid
        // race conditions in which the side effect submits an action too quickly
        if (update.sideEffect != null)
            handler.post(new Runnable() {
                @Override
                public void run() {
                    sideEffectRunner.runSideEffect(
                            Dispatcher.this, update.sideEffect);
                }
            });

    }

    /**
     * Sets a listener of state changes. This is useful only for debugging. You
     * can log every action, state and side effect or append them to a list
     * and figure out how you got to a particular state.
     * @param listener An object that listens state changes.
     */
    public void setListener(OnUpdateListener<T, U> listener) {
        this.listener = listener;
    }

    public interface OnUpdateListener<T, U> {
        public void onUpdate(Action<T, U> action,
                             T newState,
                             @Nullable U sideEffect);
    }
}

package com.gaumala.mvi;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

/**
 * <p>An alternative {@link DispatcherViewModel} meant to be used with
 * {@link androidx.lifecycle.AbstractSavedStateVMFactory} so that state
 * can be persisted after process death.
 *
 *
 * @param <T> The State type
 * @param <U> The Side-Effect type
 */
public abstract class PersistentDispatcherViewModel<T, U> extends ViewModel {
    private Dispatcher<T, U> dispatcher;
    private SavedStateHandle savedStateHandle;

    public PersistentDispatcherViewModel(Dispatcher<T, U> dispatcher,
                                         SavedStateHandle savedStateHandle) {
        this.dispatcher = dispatcher;
        this.savedStateHandle = savedStateHandle;
    }

    /**
     * <p>Returns an action sink that you can pass to your {@link BaseUI} object
     * so that you can trigger state changes from UI events like button clicks
     * or page scrolls.</p>
     *
     * @return an ActionSink instance that can submit actions.
     */
    public ActionSink<T, U> getUserActionSink() {
        return dispatcher;
    }

    /**
     * Returns a LiveData object with the application state. This is meant to
     * be passed to {@link BaseUI}'s constructor so that it can observe state
     * changes and update the UI.
     * @return a LiveData object with the application state.
     */
    @NonNull
    public LiveData<T> getLiveState() {
        return dispatcher.getLiveState();
    }

    protected abstract void writeStateToHandle(T currentState, SavedStateHandle handle);

    public void saveState() {
        T currentState = dispatcher.getLiveState().getValue();
        writeStateToHandle(currentState, savedStateHandle);
    }
}
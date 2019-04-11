package com.gaumala.mvi;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

/**
 * <p>A view model base class that simply holds the {@link Dispatcher}
 * instance used by your app. You should extend this class with your own State
 * and Side-Effect types so that your application state can persist
 * configuration changes like device rotations.</p>
 *
 * <p>Since view models can be instantiated by the android framework, you have to
 * create a {@link Dispatcher} object yourself, along with initial app state and
 * then pass it to your view model using
 * {@link BaseViewModel#setDispatcher(Dispatcher)} It is recommended that you
 * use a {@link androidx.lifecycle.ViewModelProvider.Factory} class to do this
 * so that the setup is guaranteed to run only once.</p>
 *
 * @param <T> The State type
 * @param <U> The Side-Effect type
 */
public class BaseViewModel<T, U> extends ViewModel {
    private Dispatcher<T, U> dispatcher = null;

    public boolean isInitialized() {
        return dispatcher != null;
    }

    /**
     * Constructor with no parameters. Used by the android framework.
     */
    public BaseViewModel() {}

    public BaseViewModel(Dispatcher<T, U> dispatcher) {
        this.dispatcher = dispatcher;
    }

    /**
     * setter method for the dispatcher. If it already has a Dispatch
     * instance it does nothing.
     * @param dispatcher
     */
    public void setDispatcher(
            @NonNull Dispatcher<T, U> dispatcher) {
        if (! isInitialized())
            this.dispatcher = dispatcher;
    }

    /**
     * <p>Returns an action sink that you can pass to your {@link BaseUI} object
     * so that you can trigger state changes from UI events like button clicks
     * or page scrolls.</p>
     *
     * <p>You must have initialized the view model previously by calling
     * {@link BaseViewModel#setDispatcher(Dispatcher)}.</p>
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

    /**
     * Calls the dispatcher's {@link Dispatcher#updateSilently(Object)} method.
     * @param state the new state.
     */
    public void updateSilently(T state) {
        dispatcher.updateSilently(state);
    }
}

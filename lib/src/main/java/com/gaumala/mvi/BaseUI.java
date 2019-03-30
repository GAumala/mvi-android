package com.gaumala.mvi;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

/**
 * The "view" of your application. An object that holds all your views and
 * adjusts them to reflect the current state.
 * @param <T> The type of the state.
 */
public abstract class BaseUI<T> {
    protected @NonNull LifecycleOwner owner;
    protected @NonNull LiveData<T> liveState;

    /**
     * Constructor for BaseUI.
     * @param owner an activity or fragment that owns this UI.
     * @param liveState A LiveData object with the application state.
     */
    public BaseUI(@NonNull LifecycleOwner owner, @NonNull LiveData<T> liveState) {
        this.owner = owner;
        this.liveState = liveState;
    }

    /**
     * <p>Subscribes the UI to state changes. You MUST call this method right
     * after you instantiate your layout, otherwise the UI will never update.
     * </p>
     *
     * <p>This set's a lifecycle aware observer to the application state's
     * LiveData calling {@link BaseUI#rebind(Object)} every time  it changes.
     * </p>
     */
    public void subscribe() {
        liveState.observe(owner, new Observer<T>() {
            @Override
            public void onChanged(T state) {
                if (state != null) BaseUI.this.rebind(state);
            }
        });

        T initialState = liveState.getValue();
        if (initialState != null)
            rebind(initialState);
    }

    /**
     * This method is called every time the state changes and the
     * activity/fragment that displays the UI is active. The implementation
     * should adjust its views so that they reflect the given state.
     * @param state The current state value. This is assumed to be immutable.
     *              The implementation should try the convey this value
     *              graphically to the user.
     */
    public abstract void rebind(T state);
}

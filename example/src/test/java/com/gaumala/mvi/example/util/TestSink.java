package com.gaumala.mvi.example.util;

import com.gaumala.mvi.Action;
import com.gaumala.mvi.ActionSink;
import com.gaumala.mvi.Update;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestSink<T, U> implements ActionSink<T, U> {
    private LinkedList<T> history = new LinkedList<>();
    private LinkedList<U> sideEffects = new LinkedList<>();

    public TestSink(T state) {
        history.add(state);
    }

    public T getCurrentState() {
        return history.getLast();
    }
    public void submitAction(Action<T, U> action) {
        Update<T, U> update = action.update(history.getLast());
        history.add(update.state);

        if (update.sideEffect != null)
            sideEffects.add(update.sideEffect);
    }

    public List<U> getGeneratedSideEffects() {
        return new ArrayList<>(sideEffects);
    }
}

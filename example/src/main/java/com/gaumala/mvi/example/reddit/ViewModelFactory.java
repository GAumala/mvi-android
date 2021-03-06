package com.gaumala.mvi.example.reddit;

import com.gaumala.mvi.Dispatcher;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final Fragment fragment;

    public ViewModelFactory(Fragment fragment) {
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        // The fragment's arguments could be used here to create
        // the initial state
        RedditState initialState = RedditState.createInitialState();

        RedditSideEffectRunner runner =
                new RedditSideEffectRunner(fragment.getResources());

        // optionally, startup side effects could be run here

        Dispatcher<RedditState, RedditSideEffect> dispatcher =
                new Dispatcher<>(runner, initialState);

        return (T) new RedditViewModel(dispatcher);
    }
}

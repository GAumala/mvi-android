package com.gaumala.mvi.example.reddit;

import com.gaumala.mvi.Dispatcher;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

class PersistentViewModelFactory implements ViewModelProvider.Factory {
    private final Fragment fragment;

    PersistentViewModelFactory(@NonNull Fragment fragment) {
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        RedditState state = RedditState.createInitialState();
        RedditSideEffectRunner sideEffectRunner =
                new RedditSideEffectRunner(fragment.getResources());
        Dispatcher<RedditState, RedditSideEffect> dispatcher =
                new Dispatcher<>(sideEffectRunner, state);


        return (T) new RedditViewModel(dispatcher);
    }
}

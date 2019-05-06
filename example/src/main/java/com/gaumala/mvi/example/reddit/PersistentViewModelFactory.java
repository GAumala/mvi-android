package com.gaumala.mvi.example.reddit;

import android.os.Bundle;

import com.gaumala.mvi.Dispatcher;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AbstractSavedStateVMFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

class PersistentViewModelFactory extends AbstractSavedStateVMFactory {
    private final Fragment fragment;

    PersistentViewModelFactory(@NonNull Fragment fragment) {
        super(fragment, createDefaultArgs());
        this.fragment = fragment;
    }

    @NonNull
    @Override
    protected <T extends ViewModel> T create(@NonNull String key,
                                             @NonNull Class<T> modelClass,
                                             @NonNull SavedStateHandle handle) {
        RedditState state =
                handle.get(PersistentRedditViewModel.REDDIT_STATE_KEY);
        RedditSideEffectRunner sideEffectRunner =
                new RedditSideEffectRunner(fragment.getResources());
        Dispatcher<RedditState, RedditSideEffect> dispatcher =
                new Dispatcher<>(sideEffectRunner, state);

        if (state instanceof RedditState.Loading) {
            // if app got killed while loading state,
            // we should retry the request here
            RedditState.Loading loadingState = (RedditState.Loading) state;
            RedditSideEffect sideEffect = RedditSideEffect.FetchPosts.create(
                    loadingState.subredditName());
            sideEffectRunner.runSideEffect(dispatcher, sideEffect);
        }

        return (T) new PersistentRedditViewModel(dispatcher, handle);
    }

    private static Bundle createDefaultArgs() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(
                PersistentRedditViewModel.REDDIT_STATE_KEY,
                RedditState.createInitialState());
        return bundle;
    }

}

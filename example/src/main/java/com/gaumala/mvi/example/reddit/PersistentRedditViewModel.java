package com.gaumala.mvi.example.reddit;

import com.gaumala.mvi.Dispatcher;
import com.gaumala.mvi.PersistentDispatcherViewModel;

import androidx.lifecycle.SavedStateHandle;

public class PersistentRedditViewModel
        extends PersistentDispatcherViewModel<RedditState, RedditSideEffect> {

    public PersistentRedditViewModel(Dispatcher<RedditState, RedditSideEffect> dispatcher,
                                     SavedStateHandle savedStateHandle) {
        super(dispatcher, savedStateHandle);
    }

    @Override
    protected void writeStateToHandle(RedditState currentState, SavedStateHandle handle) {
        handle.set(REDDIT_STATE_KEY, currentState);
    }

    public static String REDDIT_STATE_KEY = "RedditState";
}

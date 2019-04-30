package com.gaumala.mvi.example.reddit;

import com.gaumala.mvi.DispatcherViewModel;
import com.gaumala.mvi.Dispatcher;

public class RedditViewModel extends DispatcherViewModel<RedditState, RedditSideEffect> {

    RedditViewModel(Dispatcher<RedditState, RedditSideEffect> dispatcher) {
        super(dispatcher);
    }
}

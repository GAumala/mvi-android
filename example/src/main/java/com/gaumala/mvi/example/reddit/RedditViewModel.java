package com.gaumala.mvi.example.reddit;

import com.gaumala.mvi.BaseViewModel;
import com.gaumala.mvi.Dispatcher;

public class RedditViewModel extends BaseViewModel<RedditState, RedditSideEffect> {

    RedditViewModel(Dispatcher<RedditState, RedditSideEffect> dispatcher) {
        super(dispatcher);
    }
}

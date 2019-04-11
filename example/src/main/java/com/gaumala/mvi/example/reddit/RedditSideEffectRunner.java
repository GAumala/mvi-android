package com.gaumala.mvi.example.reddit;

import android.content.res.Resources;

import com.gaumala.mvi.example.reddit.actions.FetchPosts;
import com.gaumala.mvi.ActionSink;
import com.gaumala.mvi.SideEffectRunner;

public class RedditSideEffectRunner
        implements SideEffectRunner<RedditState, RedditSideEffect> {
    private final Resources resources;

    public RedditSideEffectRunner(Resources resources) {
        this.resources = resources;
    }

    @Override
    public void runSideEffect(ActionSink<RedditState, RedditSideEffect> sink,
                              RedditSideEffect sideEffect) {

        if (sideEffect instanceof RedditSideEffect.FetchPosts)
            fetchPosts(sink, (RedditSideEffect.FetchPosts) sideEffect);

    }

    private void fetchPosts(ActionSink<RedditState, RedditSideEffect> sink,
                            RedditSideEffect.FetchPosts sideEffect) {

        FetchPostsTask.run(
                resources,
                sideEffect.subredditName(),
                res -> sink.submitAction(FetchPosts.create(res)));

    }
}

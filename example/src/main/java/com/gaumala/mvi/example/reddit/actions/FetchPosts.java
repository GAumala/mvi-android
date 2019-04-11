package com.gaumala.mvi.example.reddit.actions;

import com.gaumala.mvi.example.reddit.RedditSideEffect;
import com.gaumala.mvi.example.reddit.RedditState;
import com.gaumala.mvi.example.reddit.results.FetchPostsRes;
import com.gaumala.mvi.Action;
import com.gaumala.mvi.Update;
import com.google.auto.value.AutoValue;

import androidx.annotation.NonNull;

@AutoValue
public abstract class FetchPosts extends Action<RedditState, RedditSideEffect> {
    public abstract FetchPostsRes res();

    @NonNull
    @Override
    public Update<RedditState, RedditSideEffect> update(RedditState currentState) {

        if (!(currentState instanceof RedditState.Loading))
            return new Update<>(currentState);

        RedditState.Loading state = (RedditState.Loading) currentState;
        FetchPostsRes res = res();
        if (res instanceof FetchPostsRes.Success)
            return updateWithSuccess(state, (FetchPostsRes.Success) res);

        return updateWithError((FetchPostsRes.Error) res);
    }

    private Update<RedditState, RedditSideEffect> updateWithError(
            FetchPostsRes.Error res) {

        RedditState newState = RedditState.Error.create(res.message());
        return new Update<>(newState);
    }

    private Update<RedditState, RedditSideEffect> updateWithSuccess(
            RedditState.Loading state,
            FetchPostsRes.Success res) {

        RedditState newState = RedditState.Ready.create(
                state.subredditName(),
                res.posts());
        return new Update<>(newState);
    }

    public static FetchPosts create(FetchPostsRes res) {
        return new AutoValue_FetchPosts(res);
    }
}

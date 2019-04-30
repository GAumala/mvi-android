package com.gaumala.mvi.example.reddit;

import com.gaumala.mvi.example.reddit.actions.CallFetchPosts;
import com.gaumala.mvi.example.reddit.actions.FetchPosts;
import com.gaumala.mvi.example.util.ResponseMocks;
import com.gaumala.mvi.example.util.TestSink;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RedditActionsTest {

    @Test
    public void should_display_posts_in_absence_of_errors() {
        RedditState initialState = RedditState.createInitialState();
        TestSink<RedditState, RedditSideEffect> sink =
                new TestSink<>(initialState);

        sink.submitAction(CallFetchPosts.create("news"));
        sink.submitAction(FetchPosts.create(
                ResponseMocks.fetchPosts_Success));

        RedditState currentState = sink.getCurrentState();
        RedditState expectedState = RedditState.Ready.create(
            "news",
            ResponseMocks.fetchPosts_Success.posts());
        assertThat(currentState, is(equalTo(expectedState)));
    }

    @Test
    public void should_generate_FetchPosts_side_effect_in_absence_of_errors() {
        RedditState initialState = RedditState.createInitialState();
        TestSink<RedditState, RedditSideEffect> sink =
                new TestSink<>(initialState);

        sink.submitAction(CallFetchPosts.create("news"));
        sink.submitAction(FetchPosts.create(
                ResponseMocks.fetchPosts_Success));


        List<RedditSideEffect> actualSideEffects = sink.getGeneratedSideEffects();
        List<RedditSideEffect> expectedSideEffects = Collections.singletonList(
                RedditSideEffect.FetchPosts.create("news"));
        assertThat(actualSideEffects, is(equalTo(expectedSideEffects)));
    }
}

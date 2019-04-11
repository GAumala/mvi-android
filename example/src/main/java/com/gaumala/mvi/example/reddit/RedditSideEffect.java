package com.gaumala.mvi.example.reddit;

import com.google.auto.value.AutoValue;

public abstract class RedditSideEffect {

    @AutoValue
    public static abstract class FetchPosts extends RedditSideEffect {
        public abstract String subredditName();

        public static FetchPosts create(String subredditName) {
            return new AutoValue_RedditSideEffect_FetchPosts(subredditName);
        }
    }
}

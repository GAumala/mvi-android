package com.gaumala.mvi.example.reddit;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

import java.util.ArrayList;

import androidx.annotation.StringRes;

public abstract class RedditState {
    /**
     * Need to input subreddit name to load posts. Show form
     */
    @AutoValue
    public static abstract class Input extends RedditState implements Parcelable {
        public abstract @StringRes
        int errorResId();

        public static Input create(int errorResId) {
            return new AutoValue_RedditState_Input(errorResId);
        }
    }

    /**
     * Posts are loading, better show a spinner in the meantime.
     */
    @AutoValue
    public static abstract class Loading extends RedditState implements Parcelable{
        public abstract String subredditName();

        public static Loading create(String subredditName) {
            return new AutoValue_RedditState_Loading(subredditName);
        }
    }

    /**
     * Posts are ready to be displayed
     */
    @AutoValue
    public static abstract class Ready extends RedditState implements Parcelable {
        public abstract String subredditName();
        public abstract ArrayList<Post> posts();

        public static Ready create(String subredditName, ArrayList<Post> posts) {
            return new AutoValue_RedditState_Ready(subredditName, posts);
        }
    }


    /**
     * Something went wrong loading the posts. Show an error message
     */
    @AutoValue
    public static abstract class Error extends RedditState implements Parcelable {
        public abstract String message();

        public static Error create(String message) {
            return new AutoValue_RedditState_Error(message);
        }
    }

    public static RedditState.Input createInitialState() {
        return Input.create(-1);
    }
}
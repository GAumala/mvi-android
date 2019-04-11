package com.gaumala.mvi.example.reddit.actions;

import com.gaumala.mvi.Action;
import com.gaumala.mvi.Update;
import com.gaumala.mvi.example.reddit.RedditSideEffect;
import com.gaumala.mvi.example.reddit.RedditState;
import com.google.auto.value.AutoValue;

import androidx.annotation.NonNull;

@AutoValue
public class ResetForm extends Action<RedditState, RedditSideEffect> {
    @NonNull
    @Override
    public Update<RedditState, RedditSideEffect> update(RedditState state) {
        return new Update<>(RedditState.Input.create(-1));
    }

    public static ResetForm create() {
        return new AutoValue_ResetForm();
    }
}

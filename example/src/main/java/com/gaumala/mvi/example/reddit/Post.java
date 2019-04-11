package com.gaumala.mvi.example.reddit;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Post {
    public abstract String title();
    public abstract String url();
    public abstract int karma();

    public static Post create(String title, String url, int karma) {
        return new AutoValue_Post(title, url, karma);
    }
}

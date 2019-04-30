package com.gaumala.mvi.example.util;

import com.gaumala.mvi.example.reddit.Post;
import com.gaumala.mvi.example.reddit.results.FetchPostsRes;

import java.util.Arrays;

public class ResponseMocks {
        public static final FetchPostsRes.Success fetchPosts_Success =
            FetchPostsRes.Success.create(Arrays.asList(
                Post.create("First Post", "__url__", 1000),
                Post.create("Second Post", "__url__", 900),
                Post.create("Third Post", "__url__", 700)
            ));
}

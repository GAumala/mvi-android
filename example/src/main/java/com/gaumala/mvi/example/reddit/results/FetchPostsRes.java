package com.gaumala.mvi.example.reddit.results;

import android.content.res.Resources;

import com.gaumala.mvi.example.R;
import com.gaumala.mvi.example.reddit.Post;
import com.google.auto.value.AutoValue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public abstract class FetchPostsRes {

    @AutoValue
    public static abstract class Success extends FetchPostsRes {
        public abstract ArrayList<Post> posts();

        public static Success create(ArrayList<Post> posts) {
            return new AutoValue_FetchPostsRes_Success(posts);
        }

        public static Post parsePost(JSONObject json) throws JSONException {
            JSONObject data = json.getJSONObject("data");

            String title = data.getString("title");
            String url = data.getString("url");
            int score = data.getInt("score");

            return Post.create(title, url, score);

        }

        public static Success parseJSON(JSONObject json) throws JSONException {
            ArrayList<Post> posts = new ArrayList<>();

            JSONObject data = json.getJSONObject("data");
            JSONArray children = data.getJSONArray("children");

            for (int i = 0; i < children.length(); i++) {
                posts.add(parsePost(children.getJSONObject(i)));
            }

            return Success.create(posts);
        }
    }

    @AutoValue
    public static abstract class Error extends FetchPostsRes {
        public abstract String message();

        public static Error create(String message) {
            return new AutoValue_FetchPostsRes_Error(message);
        }
    }

    public static FetchPostsRes fromJSON(JSONObject json, Resources resources) {
        try {
            return Success.parseJSON(json);
        } catch (JSONException e) {
            e.printStackTrace();
            return Error.create(
                    resources.getString(R.string.json_error));
        }
    }

}

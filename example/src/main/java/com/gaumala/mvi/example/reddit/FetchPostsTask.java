package com.gaumala.mvi.example.reddit;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import com.gaumala.mvi.example.R;
import com.gaumala.mvi.example.reddit.results.FetchPostsRes;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class FetchPostsTask extends AsyncTask<String, Void, FetchPostsRes> {
    private final Resources resources;
    private final OnTaskCompleteListener listener;

    public FetchPostsTask(Resources resources, OnTaskCompleteListener listener) {
        this.resources = resources;
        this.listener = listener;
    }

    private String fetchServerResponse(String subredditName) throws IOException {
        URL url = new URL("https://reddit.com/r/" + subredditName + ".json");
        InputStream inputStream = url.openStream();
        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader aBufferedInputStream =
            new BufferedReader(
                    new InputStreamReader(
                            inputStream, "UTF-8"));

            String line;
            while ((line = aBufferedInputStream.readLine()) != null) {
                stringBuilder.append(
                    new String(
                        line.getBytes("UTF-8"),
                        "UTF-8"));
            }
        } finally {
            inputStream.close();
        }

        return stringBuilder.toString();
    }

    @Override
    protected FetchPostsRes doInBackground(String... params) {
        String subredditName = params[0];
        try {
            String response = fetchServerResponse(subredditName);
            Log.d("FetchPostTask", "res " + response);
            JSONObject json = new JSONObject(response);
            return FetchPostsRes.fromJSON(json, resources);
        } catch (IOException e) {
            e.printStackTrace();
            return FetchPostsRes.Error.create(
                    resources.getString(R.string.network_error));
        } catch (JSONException e) {
            e.printStackTrace();
            return FetchPostsRes.Error.create(
                    resources.getString(R.string.json_error));
        } catch (Exception e) {
            return FetchPostsRes.Error.create(
                    resources.getString(R.string.unexpected_error));
        }
    }

    @Override
    protected void onPostExecute(FetchPostsRes fetchPostsRes) {
        listener.onTaskComplete(fetchPostsRes);
    }

    public interface OnTaskCompleteListener {
        void onTaskComplete(FetchPostsRes res);
    }

    public static void run(Resources resources,
                           String subredditName,
                           OnTaskCompleteListener listener) {

        new FetchPostsTask(resources, listener)
                .execute(subredditName);
    }
}

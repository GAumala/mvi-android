package com.gaumala.mvi.example.reddit;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.gaumala.mvi.ActionSink;
import com.gaumala.mvi.BaseUI;
import com.gaumala.mvi.example.R;
import com.gaumala.mvi.example.reddit.actions.CallFetchPosts;
import com.gaumala.mvi.example.reddit.actions.ResetForm;
import com.gaumala.mvi.example.reddit.recycler.RedditItemFactory;
import com.gaumala.mvi.example.util.Animations;
import com.google.android.material.textfield.TextInputLayout;
import com.xwray.groupie.GroupAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

class RedditGUI extends BaseUI<RedditState> {

    private final Context ctx;
    private final ActionSink<RedditState, RedditSideEffect> sink;
    private final ActionBar actionBar;

    private final View inputForm;
    private final TextInputLayout subredditInputLayout;
    private final View submitButton;
    private final GroupAdapter postsAdapter;
    private final RecyclerView postsRecycler;
    private final Toolbar toolbar;

    /**
     * Constructor for RedditGUI.
     *
     * @param owner     an activity or fragment that owns this UI.
     * @param liveState A LiveData object with the application state.
     * @param view      The root view of the UI.
     */
    RedditGUI(@NonNull LifecycleOwner owner,
              @NonNull LiveData<RedditState> liveState,
              ActionSink<RedditState, RedditSideEffect> sink,
              View view,
              ActionBar actionBar) {
        super(owner, liveState);

        this.ctx = view.getContext();
        this.sink = sink;
        this.actionBar = actionBar;
        this.postsAdapter = new GroupAdapter();

        inputForm = view.findViewById(R.id.subreddit_input_form);
        subredditInputLayout = view.findViewById(R.id.subreddit_input_layout);
        submitButton = view.findViewById(R.id.submit_button);
        postsRecycler = view.findViewById(R.id.posts_recycler);
        toolbar = view.findViewById(R.id.toolbar);

        setupRecyclers();
    }

    @Override
    public void rebind(RedditState state) {
        toolbar.setTitle(getTitle(state));

        if (state instanceof RedditState.Input)
            showInputForm((RedditState.Input) state);
        else
            showPostsRecyler(state);

    }

    private void showPostsRecyler(RedditState state) {
        Animations.switchViewsWithHorizontalSlide(
                inputForm, postsRecycler, false);
        actionBar.setDisplayHomeAsUpEnabled(true);

        postsAdapter.update(RedditItemFactory.createRecyclerItems(
                state, p -> openLink(p.url())));
    }

    private void openLink(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        ctx.startActivity(i);
    }

    private void showInputForm(RedditState.Input state) {
        Animations.switchViewsWithHorizontalSlide(
                postsRecycler, inputForm, true);

        actionBar.setDisplayHomeAsUpEnabled(false);

        if (state.errorResId() != -1)
            subredditInputLayout.setError(ctx.getString(state.errorResId()));

        submitButton.setOnClickListener(v -> {
            String inputText = subredditInputLayout
                    .getEditText().getText().toString();
            sink.submitAction(CallFetchPosts.create(inputText));
        });
    }

    private String getTitle(RedditState state) {
        if (state instanceof RedditState.Loading)
            return "/r/" + ((RedditState.Loading) state).subredditName();

        if (state instanceof RedditState.Ready)
            return "/r/" + ((RedditState.Ready) state).subredditName();

        return ctx.getString(R.string.reddit);
    }

    private void setupRecyclers() {
        postsRecycler.setAdapter(postsAdapter);

        DividerItemDecoration divider =
                new DividerItemDecoration(ctx, DividerItemDecoration.VERTICAL);
        postsRecycler.addItemDecoration(divider);
    }

    boolean onBackPressed() {
        RedditState state = liveState.getValue();
        if (state instanceof RedditState.Input)
            return false;
        else {
            subredditInputLayout.getEditText().setText("");
            sink.submitAction(ResetForm.create());
            return true;
        }
    }
}

package com.gaumala.mvi.example.reddit.recycler;

import com.gaumala.mvi.example.R;
import com.gaumala.mvi.example.databinding.PostItemBinding;
import com.gaumala.mvi.example.reddit.OnPostClickedListener;
import com.gaumala.mvi.example.reddit.Post;
import com.xwray.groupie.databinding.BindableItem;

import androidx.annotation.NonNull;

public class PostItem extends BindableItem<PostItemBinding> {
    private final Post post;
    private final OnPostClickedListener listener;

    PostItem(Post post, OnPostClickedListener listener) {
        this.post = post;
        this.listener = listener;
    }

    @Override
    public void bind(@NonNull PostItemBinding viewBinding, int position) {
        viewBinding.karmaText.setText("" + post.karma());
        viewBinding.titleText.setText(post.title());

        viewBinding.getRoot().setOnClickListener(v ->
                listener.onPostClicked(post));
    }

    @Override
    public int getLayout() {
        return R.layout.post_item;
    }
}

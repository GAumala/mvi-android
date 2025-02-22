package com.gaumala.mvi.example.reddit.recycler;

import android.view.View;

import com.gaumala.mvi.example.R;
import com.gaumala.mvi.example.databinding.LoadingItemBinding;
import com.xwray.groupie.viewbinding.BindableItem;

import androidx.annotation.NonNull;

public class LoadingItem extends BindableItem<LoadingItemBinding> {
    @NonNull
    @Override
    protected LoadingItemBinding initializeViewBinding(@NonNull View view) {
        return LoadingItemBinding.bind(view);
    }

    @Override
    public void bind(@NonNull LoadingItemBinding viewBinding, int position) {
    }

    @Override
    public int getLayout() {
        return R.layout.loading_item;
    }
}

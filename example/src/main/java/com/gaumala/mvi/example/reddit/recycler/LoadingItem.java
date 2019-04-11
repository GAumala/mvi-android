package com.gaumala.mvi.example.reddit.recycler;

import com.gaumala.mvi.example.R;
import com.gaumala.mvi.example.databinding.LoadingItemBinding;
import com.xwray.groupie.databinding.BindableItem;

import androidx.annotation.NonNull;

public class LoadingItem extends BindableItem<LoadingItemBinding> {
    @Override
    public void bind(@NonNull LoadingItemBinding viewBinding, int position) {
    }

    @Override
    public int getLayout() {
        return R.layout.loading_item;
    }
}

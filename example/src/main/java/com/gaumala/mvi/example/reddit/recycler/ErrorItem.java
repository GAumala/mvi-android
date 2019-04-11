package com.gaumala.mvi.example.reddit.recycler;

import com.gaumala.mvi.example.R;
import com.gaumala.mvi.example.databinding.ErrorItemBinding;
import com.xwray.groupie.databinding.BindableItem;

import androidx.annotation.NonNull;

public class ErrorItem extends BindableItem<ErrorItemBinding> {
    private final String message;

    public ErrorItem(String message) {
        this.message = message;
    }

    @Override
    public void bind(@NonNull ErrorItemBinding viewBinding, int position) {
        viewBinding.errorText.setText(message);
    }

    @Override
    public int getLayout() {
        return R.layout.error_item;
    }
}

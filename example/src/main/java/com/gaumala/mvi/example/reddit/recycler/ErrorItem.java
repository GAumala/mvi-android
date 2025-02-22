package com.gaumala.mvi.example.reddit.recycler;

import android.view.View;

import com.gaumala.mvi.example.R;
import com.gaumala.mvi.example.databinding.ErrorItemBinding;
import com.xwray.groupie.viewbinding.BindableItem;

import androidx.annotation.NonNull;

public class ErrorItem extends BindableItem<ErrorItemBinding> {
    private final String message;

    public ErrorItem(String message) {
        this.message = message;
    }

    @NonNull
    @Override
    protected ErrorItemBinding initializeViewBinding(@NonNull View view) {
        return ErrorItemBinding.bind(view);
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

package com.gaumala.mvi.example.reddit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.gaumala.mvi.example.R;
import com.gaumala.mvi.example.util.OnBackPressedListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class RedditFragment extends Fragment implements OnBackPressedListener {
    private RedditGUI gui;
    private RedditViewModel viewModel;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
        }
        return true;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        viewModel = ViewModelProviders
                .of(this, new PersistentViewModelFactory(this))
                .get(RedditViewModel.class);

        View view = inflater.inflate(
                R.layout.reddit_fragment, container, false);
        ActionBar actionBar = setupActionBar(view.findViewById(R.id.toolbar));

        gui = new RedditGUI(
                this.getViewLifecycleOwner(),
                viewModel.getLiveState(),
                viewModel.getUserActionSink(),
                view, actionBar);
        gui.subscribe();

        return view;
    }

    private ActionBar setupActionBar(Toolbar toolbar) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        return activity.getSupportActionBar();
    }

    @Override
    public boolean onBackPressed() {
        return gui.onBackPressed();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

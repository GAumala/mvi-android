package com.gaumala.mvi.example;

import android.os.Bundle;

import com.gaumala.mvi.example.reddit.RedditFragment;
import com.gaumala.mvi.example.util.OnBackPressedListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
    }

    private void setupInitialFragment(FragmentManager manager) {
        Fragment fragment = new RedditFragment();
        FragmentTransaction transaction =  manager.beginTransaction();
        transaction.replace(R.id.root_layout, fragment);
        transaction.commit();
    }

    private void setupUI() {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.findFragmentById(R.id.root_layout) != null)
            return; // ya hay un fragment en el activity

        setupInitialFragment(manager);
    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        Fragment currentFragment = manager.findFragmentById(R.id.root_layout);

        if (! (currentFragment instanceof OnBackPressedListener)) {
            super.onBackPressed();
            return;
        }

        boolean shouldSwallowBackEvent =
                ((OnBackPressedListener) currentFragment).onBackPressed();
        if (! shouldSwallowBackEvent)
            super.onBackPressed();
    }


}

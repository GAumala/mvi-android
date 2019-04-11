package com.gaumala.mvi.example.util;

public interface OnBackPressedListener {
    /**
     *
     * @return true if activity should swallow the back event instead of
     * letting the system handle it, which could eventually lead to activity
     * getting closed.
     */
    public boolean onBackPressed();
}

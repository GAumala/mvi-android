package com.gaumala.mvi.example.util;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

public class Animations {


    public static void switchViewsWithHorizontalSlide(final View out,
                                                      final View in,
                                                      boolean reverse) {
        if ((out.getVisibility() != View.VISIBLE)
                || (in.getVisibility() == View.VISIBLE)) {
            out.setVisibility(View.GONE);
            in.setVisibility(View.VISIBLE);
            return;
        }

        float outStartX = 0;
        float outEndX = reverse ? out.getWidth() : - out.getWidth();
        float inStartX = reverse ? - out.getWidth() : out.getWidth();
        float inEndX = outStartX;

        ObjectAnimator outSlideAnimator = ObjectAnimator.ofFloat(out,
                "translationX", outStartX, outEndX);
        ObjectAnimator inSlideAnimator = ObjectAnimator.ofFloat(in,
                "translationX", inStartX, inEndX);
        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.play(outSlideAnimator)
                .with(inSlideAnimator);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                in.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                out.setVisibility(View.GONE);
                in.setVisibility(View.VISIBLE);
                out.setTranslationX(0);
                in.setTranslationX(0);
            }

            @Override
            public void onAnimationCancel(Animator animation) { }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        set.start();
    }
}

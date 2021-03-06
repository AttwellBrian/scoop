package com.example.scoop.basics.ui.transitions.dialogtransitions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import com.lyft.scoop.TransitionListener;
import com.lyft.scoop.transitions.ObjectAnimatorTransition;

public class SlideUpTransition extends ObjectAnimatorTransition {

    @Override
    public void performTranslate(final ViewGroup root, final View from, View to, final TransitionListener transitionListener) {
        if (to == null) {
            root.removeView(from);
            return;
        }

        Animator animator = createAnimator(to);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                root.removeView(from);
                transitionListener.onTransitionCompleted();
            }
        });
        animator.start();
    }

    private Animator createAnimator(View from) {
        int toTranslation = from.getHeight();

        AnimatorSet set = new AnimatorSet();

        set.play(ObjectAnimator.ofFloat(from, View.TRANSLATION_Y, toTranslation, 0));

        return set;
    }
}

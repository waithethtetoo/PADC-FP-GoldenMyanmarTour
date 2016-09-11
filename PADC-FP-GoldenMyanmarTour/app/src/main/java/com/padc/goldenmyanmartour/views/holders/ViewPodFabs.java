package com.padc.goldenmyanmartour.views.holders;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;

import com.padc.goldenmyanmartour.R;

/**
 * Created by WT on 9/11/2016.
 */
public class ViewPodFabs extends FrameLayout {

    private FloatingActionButton fabBookMark;
    private FloatingActionButton fabShare;
    private FloatingActionButton fabInfo;
    private boolean isOpen = false;

    public ViewPodFabs(Context context) {
        super(context);
    }

    public ViewPodFabs(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewPodFabs(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void openAnim() {
        ObjectAnimator objAnim = ObjectAnimator.ofFloat(fabInfo, "rotation", 540f, 0f);
        objAnim.setDuration(600);
        objAnim.setInterpolator(new AccelerateInterpolator());
        objAnim.start();

        ObjectAnimator objAnimBookMarkFW = ObjectAnimator.ofFloat(fabBookMark, "y", fabBookMark.getY(), fabBookMark.getY() - 310f);
        objAnimBookMarkFW.setDuration(500);
        objAnimBookMarkFW.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator objAnimBookMarkBW = ObjectAnimator.ofFloat(fabBookMark, "y", fabBookMark.getY() - 310, fabBookMark.getY() - 280f);
        objAnimBookMarkBW.setDuration(100);
        objAnimBookMarkBW.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet asBookMark = new AnimatorSet();
        asBookMark.play(objAnimBookMarkBW).after(objAnimBookMarkFW);

        ObjectAnimator objAnimShareXBW = ObjectAnimator.ofFloat(fabShare, "x", fabShare.getX() - 180f, fabShare.getX() - 160f);
        objAnimShareXBW.setDuration(100);
        objAnimShareXBW.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator objAnimShareYBW = ObjectAnimator.ofFloat(fabShare, "y", fabShare.getY() - 180f, fabShare.getY() - 160f);
        objAnimShareYBW.setDuration(100);
        objAnimShareYBW.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet asShareBW = new AnimatorSet();
        asShareBW.play(objAnimShareYBW).with(objAnimShareXBW);
        asShareBW.setStartDelay(500);
        asShareBW.start();
    }

    private void closeAnim() {
        ObjectAnimator objAnimRotation = ObjectAnimator.ofFloat(fabInfo, "rotation", 0, 540f);
        objAnimRotation.setDuration(600);
        objAnimRotation.setInterpolator(new AccelerateInterpolator());
        objAnimRotation.start();

        ObjectAnimator objAnimBookMarkFW = ObjectAnimator.ofFloat(fabBookMark, "y", fabBookMark.getY(), fabBookMark.getY() - 30f);
        objAnimBookMarkFW.setDuration(100);
        objAnimBookMarkFW.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator objAnimBookMarkBW = ObjectAnimator.ofFloat(fabBookMark, "y", fabBookMark.getY() - 30, fabBookMark.getY() + 280f);
        objAnimBookMarkBW.setDuration(500);
        objAnimBookMarkBW.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet asBookMark = new AnimatorSet();
        asBookMark.play(objAnimBookMarkBW).after(objAnimBookMarkFW);
        asBookMark.start();

        ObjectAnimator objAnimShareXFW = ObjectAnimator.ofFloat(fabShare, "x", fabShare.getX(), fabShare.getX() - 20);
        objAnimShareXFW.setDuration(100);
        objAnimShareXFW.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator objAnimShareYFW = ObjectAnimator.ofFloat(fabShare, "y", fabShare.getY(), fabShare.getY() - 20);
        objAnimShareYFW.setDuration(100);
        objAnimShareYFW.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet asShareFW = new AnimatorSet();
        asShareFW.play(objAnimShareYFW).with(objAnimShareXFW);
        asShareFW.start();

        ObjectAnimator objAnimShareXBW = ObjectAnimator.ofFloat(fabShare, "x", fabShare.getX() - 20f, fabShare.getX() + 160f);
        objAnimShareXBW.setDuration(500);
        objAnimShareXBW.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator objAnimShareYBW = ObjectAnimator.ofFloat(fabShare, "y", fabShare.getY() - 20f, fabShare.getY() + 160f);
        objAnimShareYBW.setDuration(500);
        objAnimShareYBW.setInterpolator(new AccelerateDecelerateInterpolator());

        AnimatorSet asShareBW = new AnimatorSet();
        asShareBW.play(objAnimShareYBW).with(objAnimShareXBW);
        asShareBW.setStartDelay(100);
        asShareBW.start();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        fabInfo = (FloatingActionButton) findViewById(R.id.fab_info);
        fabInfo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOpen) {
                    openAnim();
                    isOpen = true;
                } else {
                    closeAnim();
                    isOpen = false;
                }
            }
        });
        fabBookMark = (FloatingActionButton) findViewById(R.id.fab_bookmark);
        fabBookMark.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        fabShare = (FloatingActionButton) findViewById(R.id.fab_share);
        fabShare.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
}

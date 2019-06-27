package com.chenyue404.constraintlayoutdemo;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.transition.AutoTransition;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;

import java.util.ArrayList;

public class VoiceAnimationActivity extends AppCompatActivity {

    private View v_0;
    private View v_1;
    private View v_2;
    private View v_3;
    private View v_4;
    private View v_5;
    private View v_6;
    private View v_7;
    private View v_8;
    private View v_9;
    private View v_10;
    private View v_11;
    private ConstraintLayout cl_root;

    private ConstraintSet constraintSet;
    private View[] views;
    private ArrayList<Integer> heightList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_animation);
        findId();

        v_11.postDelayed(new Runnable() {
            @Override
            public void run() {
                heightList = new ArrayList<>();
                for (View view : views) {
                    heightList.add(view.getHeight());
                }
                anim0();
            }
        }, 300);
    }

    private void findId() {
        v_0 = (View) findViewById(R.id.v_0);
        v_1 = (View) findViewById(R.id.v_1);
        v_2 = (View) findViewById(R.id.v_2);
        v_3 = (View) findViewById(R.id.v_3);
        v_4 = (View) findViewById(R.id.v_4);
        v_5 = (View) findViewById(R.id.v_5);
        v_6 = (View) findViewById(R.id.v_6);
        v_7 = (View) findViewById(R.id.v_7);
        v_8 = (View) findViewById(R.id.v_8);
        v_9 = (View) findViewById(R.id.v_9);
        v_10 = (View) findViewById(R.id.v_10);
        v_11 = (View) findViewById(R.id.v_11);
        cl_root = (ConstraintLayout) findViewById(R.id.cl_root);
        views = new View[]{v_0, v_1, v_2, v_3, v_4, v_5, v_6, v_7, v_8, v_9, v_10, v_11};

        constraintSet = new ConstraintSet();
        constraintSet.clone(cl_root);
    }

    private void anim0() {
        for (int i = 0; i < views.length; i++) {
            View v = views[i];
            constraintSet.constrainHeight(v.getId(), (int) (heightList.get(i) * (Math.random()) + 1));
        }

        TransitionSet set = new TransitionSet()
                .addTransition(new AutoTransition())
                .setDuration(300)
                .addListener(new Transition.TransitionListener() {
                    @Override
                    public void onTransitionStart(Transition transition) {

                    }

                    @Override
                    public void onTransitionEnd(Transition transition) {
                        anim1();
                    }

                    @Override
                    public void onTransitionCancel(Transition transition) {

                    }

                    @Override
                    public void onTransitionPause(Transition transition) {

                    }

                    @Override
                    public void onTransitionResume(Transition transition) {

                    }
                });
        TransitionManager.beginDelayedTransition(cl_root, set);
        constraintSet.applyTo(cl_root);
    }

    private void anim1() {
        for (int i = 0; i < views.length; i++) {
            View v = views[i];
            constraintSet.constrainHeight(v.getId(), (int) (heightList.get(i) * Math.random()));
        }
        TransitionSet set = new TransitionSet()
                .addTransition(new AutoTransition())
                .setDuration(300)
                .addListener(new Transition.TransitionListener() {
                    @Override
                    public void onTransitionStart(Transition transition) {

                    }

                    @Override
                    public void onTransitionEnd(Transition transition) {
                        anim0();
                    }

                    @Override
                    public void onTransitionCancel(Transition transition) {

                    }

                    @Override
                    public void onTransitionPause(Transition transition) {

                    }

                    @Override
                    public void onTransitionResume(Transition transition) {

                    }
                });
        TransitionManager.beginDelayedTransition(cl_root, set);
        constraintSet.applyTo(cl_root);
    }
}

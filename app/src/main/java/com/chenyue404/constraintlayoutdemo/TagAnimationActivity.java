package com.chenyue404.constraintlayoutdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.transition.AutoTransition;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TagAnimationActivity extends AppCompatActivity {

    private ConstraintLayout cl_root;
    private TextView tv_01, tv_02;
    private Button bt_move;

    private ConstraintSet constraintSet;
    private int top = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_animation);

        cl_root = findViewById(R.id.cl_root);
        tv_01 = findViewById(R.id.tv_01);
        tv_02 = findViewById(R.id.tv_02);
        bt_move = findViewById(R.id.bt_move);

        constraintSet = new ConstraintSet();
        constraintSet.clone(cl_root);

        bt_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bt_move.setEnabled(false);
                TransitionSet set;
                if (top == 1) {
                    constraintSet.clear(tv_01.getId(), ConstraintSet.TOP);
                    constraintSet.connect(tv_01.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
                    constraintSet.connect(tv_02.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
                    constraintSet.connect(tv_02.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);

                    set = new TransitionSet()
                            .addTransition(new AutoTransition())
                            .setDuration(300)
                            .addListener(new Transition.TransitionListener() {
                                @Override
                                public void onTransitionStart(Transition transition) {
                                }

                                @Override
                                public void onTransitionEnd(Transition transition) {
                                    constraintSet.clear(tv_01.getId(), ConstraintSet.BOTTOM);
                                    constraintSet.connect(tv_01.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
                                    constraintSet.applyTo(cl_root);

                                    top = 2;
                                    bt_move.setEnabled(true);
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

                } else {
                    constraintSet.clear(tv_02.getId(), ConstraintSet.TOP);
                    constraintSet.connect(tv_02.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
                    constraintSet.connect(tv_01.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
                    constraintSet.connect(tv_01.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);

                    set = new TransitionSet()
                            .addTransition(new AutoTransition())
                            .setDuration(300)
                            .addListener(new Transition.TransitionListener() {
                                @Override
                                public void onTransitionStart(Transition transition) {
                                }

                                @Override
                                public void onTransitionEnd(Transition transition) {
                                    constraintSet.clear(tv_02.getId(), ConstraintSet.BOTTOM);
                                    constraintSet.connect(tv_02.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
                                    constraintSet.applyTo(cl_root);

                                    top = 1;
                                    bt_move.setEnabled(true);
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
                }

                TransitionManager.beginDelayedTransition(cl_root, set);
                constraintSet.applyTo(cl_root);
            }
        });
    }
}

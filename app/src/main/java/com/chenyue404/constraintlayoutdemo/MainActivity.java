package com.chenyue404.constraintlayoutdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String TAG = "DEMO";
    private ConstraintSet constraintSet = new ConstraintSet();
    private TextView tv_05;
    private ConstraintLayout cl_root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findId();

    }

    public void jumpToClock(View view) {
        startActivity(new Intent(this, VoiceAnimationActivity.class));
    }

    public void packed(View view) {
        constraintSet.setVerticalChainStyle(tv_05.getId(), ConstraintSet.CHAIN_PACKED);
        TransitionManager.beginDelayedTransition(cl_root);
        constraintSet.applyTo(cl_root);
    }

    public void spread(View view) {
        constraintSet.setVerticalChainStyle(tv_05.getId(), ConstraintSet.CHAIN_SPREAD);
//        TransitionManager.beginDelayedTransition(cl_root);
        constraintSet.applyTo(cl_root);
    }

    private void findId() {
        tv_05 = findViewById(R.id.tv_05);
        cl_root = findViewById(R.id.cl_root);

        constraintSet.clone(cl_root);
    }

    public void gpVisible(View view) {
        View gp = findViewById(R.id.gp_0);

        constraintSet.setVisibility(R.id.gp_0, gp.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        TransitionManager.beginDelayedTransition(cl_root);
        constraintSet.applyTo(cl_root);

    }
}

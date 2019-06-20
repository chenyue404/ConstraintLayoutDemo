package com.chenyue404.constraintlayoutdemo;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.TextClock;

public class ClockActivity extends AppCompatActivity {
    private View v_sec;
    private View v_min;
    private View v_hour;
    private ConstraintLayout cl_root;
    private AnalogClock analogClock;
    private TextClock textClock;


    private Handler timeHandler = new Handler();
    private Runnable runnable;
    private ConstraintSet constraintSet = new ConstraintSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        findId();
        setTime();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onDestroy() {
        timeHandler.removeCallbacks(runnable);
        super.onDestroy();
    }

    private void setTime() {
        long currentTime = System.currentTimeMillis() / 1000;
        float sec_angle = (currentTime % 60) / (float) 60 * 360;
        float min_angle = (currentTime / 60 % 60) / (float) 60 * 360;
        float hour_angle = ((currentTime / 60 / 60 % 24) + 8) / (float) 12 * 360
                + (currentTime / 60 % 60) / (float) 60 * 30;

        constraintSet.setRotation(R.id.v_sec, sec_angle);
        constraintSet.setRotation(R.id.v_min, min_angle);
        constraintSet.setRotation(R.id.v_hour, hour_angle);

        constraintSet.constrainCircle(R.id.v_sec, R.id.v_center, dpToPx(60), sec_angle);
        constraintSet.constrainCircle(R.id.v_min, R.id.v_center, dpToPx(40), min_angle);
        constraintSet.constrainCircle(R.id.v_hour, R.id.v_center, dpToPx(35), hour_angle);


//        TransitionManager.beginDelayedTransition(cl_root);
        constraintSet.applyTo(cl_root);

        runnable = new Runnable() {
            @Override
            public void run() {
                setTime();
            }
        };
        timeHandler.postDelayed(runnable, 1000);
    }

    private void findId() {
        v_sec = (View) findViewById(R.id.v_sec);
        v_min = (View) findViewById(R.id.v_min);
        v_hour = (View) findViewById(R.id.v_hour);
        cl_root = findViewById(R.id.cl_root);
        analogClock = findViewById(R.id.analogClock);
        textClock = findViewById(R.id.textClock);

        constraintSet.clone(cl_root);
    }

    public int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}

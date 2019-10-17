package com.lsw.carmanager.activity;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.lsw.carmanager.R;

public class FrameAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);
        imageView = (ImageView) findViewById(R.id.imageView);
        findViewById(R.id.start).setOnClickListener(this);
        animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.setOneShot(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                if (animationDrawable != null) {
                    if(animationDrawable.isRunning()){
                        animationDrawable.stop();
                    }
                    animationDrawable.start();
                }
                break;

            default:
                break;
        }
    }
}

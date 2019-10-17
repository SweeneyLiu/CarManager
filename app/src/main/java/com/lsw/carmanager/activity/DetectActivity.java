package com.lsw.carmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lsw.carmanager.R;

public class DetectActivity extends AppCompatActivity {

    private Button detectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect);
        initView();
        detectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetectActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        detectBtn = (Button) findViewById(R.id.detectBtn);
    }
}

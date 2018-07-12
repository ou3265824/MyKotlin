package com.shanghaizhida.mykotlin;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Handler handler=new Handler();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
            }
        };
    }
}

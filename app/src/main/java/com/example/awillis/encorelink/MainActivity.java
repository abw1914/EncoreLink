package com.example.awillis.encorelink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//The main activity class will be preempted by SplashScreen
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

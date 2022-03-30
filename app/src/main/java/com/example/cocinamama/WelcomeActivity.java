package com.example.cocinamama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void goLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void goRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
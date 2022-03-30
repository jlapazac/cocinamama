package com.example.cocinamama.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cocinamama.MainActivity;
import com.example.cocinamama.R;
import com.example.cocinamama.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void goRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void login(View view){
        startActivity(new Intent(this, MainActivity.class));
    }
}
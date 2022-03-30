package com.example.cocinamama.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cocinamama.login.LoginActivity;
import com.example.cocinamama.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void goLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void goAddress(View view) {
        startActivity(new Intent(this, AddAddressActivity.class));
    }

    public void goPhoto(View view) {
        startActivity(new Intent(this, UploadPhotoActivity.class));
    }
}
package com.example.cocinamama.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cocinamama.R;

public class UploadPhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_photo);
    }

    public void goRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
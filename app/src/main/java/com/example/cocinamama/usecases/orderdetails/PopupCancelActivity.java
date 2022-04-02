package com.example.cocinamama.usecases.orderdetails;

import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cocinamama.R;

public class PopupCancelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_cancel);

        DisplayMetrics metricsPopup = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metricsPopup);

        int width = metricsPopup.widthPixels;
        int height = metricsPopup.heightPixels;

        getWindow().setLayout((int)(width * 0.85),(int)(height * 0.25));
    }
}
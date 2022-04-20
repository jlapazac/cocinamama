package com.example.cocinamama.usecases.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.cocinamama.MainActivity;
import com.example.cocinamama.R;
import com.example.cocinamama.usecases.register.RegisterActivity;

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

    //public void login(View v){
    //    EditText txtUser = (EditText)findViewById(R.id.editTextEmail);
    //    EditText txtPass = (EditText)findViewById(R.id.editTextPassword);

    //   String user = txtUser.getText().toString();
    //  String pass = txtPass.getText().toString();
    //  String url = "http://3.141.134.92:8000/user/"+user;

    //  StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

    //              @Override
    //              public void onResponse(String response) {

    //              }
    //          },
    //          new Response.ErrorListener() {
    //              @Override
    //              public void onErrorResponse(VolleyError error) {
    //                  Log.i("======>", error.toString());
    //              }
    //          }
    //);
    //}
}
package com.example.cocinamama.usecases.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cocinamama.R;
import com.example.cocinamama.usecases.login.LoginActivity;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    //public void goLogin(View view) {
    //    startActivity(new Intent(this, LoginActivity.class));
    //}

    public void goAddress(View view) {
        startActivity(new Intent(this, AddAddressActivity.class));
    }

    public void goPhoto(View view) {
        startActivity(new Intent(this, UploadPhotoActivity.class));
    }

    public void goLogin(View v){
        final EditText txtUserName = (EditText)findViewById(R.id.editTextNameReg);
        final EditText txtUserLastName = (EditText)findViewById(R.id.editTextLastNameReg);
        final EditText txtEmail = (EditText)findViewById(R.id.editTextEmailReg);
        final EditText txtPassword = (EditText)findViewById(R.id.editTextPasswordReg);
        final EditText txtPhone = (EditText)findViewById(R.id.editTextPhoneReg);

        String userName = txtUserName.getText().toString();
        String userLastName = txtUserLastName.getText().toString();
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();
        String phone = txtPhone.getText().toString();
        String photo = "photo";
        //String url = "http://3.141.134.92:8000/user/";
        String url = "http://apaza.pe:8000/user/";
        Log.i("======>", "Llegué al método registrar");
        Log.i("======>", userName);
        Log.i("======>", userLastName);
        Log.i("======>", email);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("======>", "Entré al onResponse");
                Toast toast = Toast.makeText(RegisterActivity.this,"Se registró el usuario correctamente", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                //startActivity(new Intent(this, LoginActivity.class));
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("======>", error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Log.i("======>", "Entré al Map");
                Map<String, String> params = new HashMap();
                params.put("name", userName);
                params.put("email", email);
                params.put("password", password);
                params.put("phone", phone);
                params.put("photo", photo);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
package com.example.cocinamama.usecases.register;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cocinamama.R;
import com.example.cocinamama.usecases.login.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    Bundle bundle;
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

        String url = "https://upc.apaza.pe/user/";
        Log.i("======>", "Llegué al método registrar");
        Log.i("======>", userName);
        Log.i("======>", userLastName);
        Log.i("======>", email);

        final JSONObject body = new JSONObject();
        try {
            body.put("name", userName + " " + userLastName);
            body.put("email", email);
            body.put("password", password);
            body.put("phone", phone);
            body.put("photo", photo);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject data = new JSONObject(response);

                    int idUser = data.getInt("id");
                    saveAddress(idUser);
                }catch(JSONException e){
                    e.printStackTrace();
                }

                Log.i("======>", response);
                Toast toast = Toast.makeText(RegisterActivity.this,"Se registró el usuario correctamente", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
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
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                return body.toString().getBytes();
            }

        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        startActivity(new Intent(this, LoginActivity.class));
    }

    private void saveAddress(int idUser) {
        String city = getIntent().getStringExtra("city");
        String province = getIntent().getStringExtra("province");
        String district = getIntent().getStringExtra("district");
        String address = getIntent().getStringExtra("address");

        String url = "https://upc.apaza.pe/address/";
        Log.i("============>",Integer.toString(idUser));
        final JSONObject body = new JSONObject();
        try {
            body.put("city", "city");
            body.put("province", "province");
            body.put("district", "district");
            body.put("address", "address");
            body.put("latitude", 0);
            body.put("longitude", 0);
            body.put("user_id", idUser);
            Log.i("===========>",body.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("======>", "Entré al onResponse");
                        //Toast toast = Toast.makeText(RegisterActivity.this,"Se registró el usuario correctamente", Toast.LENGTH_LONG);
                        //toast.setGravity(Gravity.CENTER, 0, 0);
                        //toast.show();
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
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                return body.toString().getBytes();
            }

        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
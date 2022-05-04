package com.example.cocinamama.usecases.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.cocinamama.MainActivity;
import com.example.cocinamama.R;
import com.example.cocinamama.usecases.register.RegisterActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    String login;
    Integer go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void goRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    /*public void login(View view){
        startActivity(new Intent(this, MainActivity.class));
    }*/

    public void login(View v){
        EditText txtEmail = (EditText)findViewById(R.id.editTextEmail);
        EditText txtPass = (EditText)findViewById(R.id.editTextPassword);

       String email = txtEmail.getText().toString();
      String pass = txtPass.getText().toString();
      String url = "https://upc.apaza.pe/user/login/";

        final JSONObject body = new JSONObject();
        try {
            body.put("email", email);
            body.put("password", pass);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //login = new String(response);
                        //Log.i("======>", login);
                        Log.i("======>", response.toString());
                        if (response != "false"){
                            Log.i("======>", "Entré al onResponse");
                            Toast toast = Toast.makeText(LoginActivity.this,"Bienvenido", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                            go = new Integer(1);
                        }else {
                            Toast toast = Toast.makeText(LoginActivity.this,"El usuario o clave son incorrectos", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                            go = new Integer(2);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("======>", error.toString());
                        Toast toast = Toast.makeText(LoginActivity.this,"El usuario o clave son incorrectos", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
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
        Log.i("======>", "GO es igual a" + requestQueue.toString());
        //Log.i("======>", "GO es igual a" + go.toString());
        /*if (go != 2){
            Log.i("======>", "GO es igual a" + go.toString());*/
        startActivity(new Intent(this, MainActivity.class));
        /*}
        else {
            Log.i("======>", "GO es igual a 2");
        }*/
    }
}
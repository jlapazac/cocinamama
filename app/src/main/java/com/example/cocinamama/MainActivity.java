package com.example.cocinamama;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cocinamama.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    private TextView txtvdireccion;
    private RequestQueue rq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navHostContainer);
        NavController navController = navHostFragment.getNavController();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        txtvdireccion=findViewById(R.id.txtvdireccion);
        rq= Volley.newRequestQueue(this);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    /*public void recuperar(View v){

        txtvdireccion.setText("");
        String url="http://3.141.134.92:8000/order/";
        JsonArrayRequest requerimiento=new JsonArrayRequest(Request.Method.GET,

                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                for { Log f
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        rq.add(requerimiento);

    }*/
}
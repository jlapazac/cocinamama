package com.example.cocinamama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class VerPedidos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pedidos);
    }

    public void verpedidosconfirmados (View v){

        startActivity(new Intent(this,VerPedidosC.class));
    }

}